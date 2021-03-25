package nl.tudelft.oopp.g72.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nl.tudelft.oopp.g72.MainApp;
import nl.tudelft.oopp.g72.entities.Question;
import nl.tudelft.oopp.g72.entities.QuestionListCell;
import nl.tudelft.oopp.g72.entities.QuestionListSelectionModel;
import nl.tudelft.oopp.g72.entities.User;
import nl.tudelft.oopp.g72.localvariables.LocalVariables;

public class LoginController {

    @FXML // Display name input field.
    private TextField displayName;
    @FXML // Room code input field.
    private TextField roomCode;


    private boolean login() throws IOException, InterruptedException {
        if (displayName.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "You haven't filled in a display name!");
            alert.show();
            return false;
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create("http://localhost:8080/api/v1/login/"))
                .POST(HttpRequest.BodyPublishers.ofString(displayName.getText()))
                .build();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() / 100 != 2) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Server error");
            alert.show();
            return false;
        }

        LocalVariables.token = response.body();
        System.out.println(LocalVariables.token);

        return true;
    }

    /**
     * Executed when the 'join room' button is clicked.
     */
    public void joinRoom() throws IOException, InterruptedException {
        // Will be executed when 'join' button is clicked.
        if (LocalVariables.token == null) {
            boolean loggedin = login();
            if (!loggedin) {
                return;
            }
        }

        if (roomCode.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "You haven't filled in a join code!");
            alert.show();
            return;
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create("http://localhost:8080/api/v1/join/"))
                .header("Token", LocalVariables.token)
                .header("Code", roomCode.getText())
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() / 100 != 2) {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> map = mapper.readValue(response.body(), Map.class);
            String message = map.get("message");
            System.out.println(message);

            if (message.equals("There are no rooms with that code!")) {
                Alert alert = new Alert(Alert.AlertType.ERROR, message);
                alert.show();
                return;
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Server error");
                alert.show();
                return;
            }
        }

        LocalVariables.roomId = Long.parseLong(response.body());
        System.out.println(LocalVariables.roomId);

        HttpClient client2 = HttpClient.newHttpClient();
        HttpRequest request2 = HttpRequest.newBuilder(
                URI.create("http://localhost:8080/api/v1/isMod"))
                .header("Token", LocalVariables.token)
                .build();
        HttpResponse<String> response2 = client2.send(
                request2, HttpResponse.BodyHandlers.ofString());
        System.out.println(response2.body());

        if (response2.body().equals("false")) {
            MainApp.window.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("/fxml/student_view.fxml"))));
        } else {
            MainApp.window.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("/fxml/teacher_view.fxml"))));
        }

    }

    /**
     * Executed when the 'create room' button is clicked.
     */
    public void createRoom() throws IOException, InterruptedException {
        // Will be executed when 'create room' button is clicked.
        if (LocalVariables.token == null) {
            boolean loggedin = login();
            if (!loggedin) {
                return;
            }
        }

        Stage dia = new Stage();
        dia.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/room_creator.fxml"))));
        dia.initModality(Modality.APPLICATION_MODAL);
        dia.requestFocus();
        dia.showAndWait();

        MainApp.window.setScene(new Scene(
                FXMLLoader.load(getClass().getResource("/fxml/teacher_view.fxml"))));
    }
}
