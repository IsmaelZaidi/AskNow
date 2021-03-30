package nl.tudelft.oopp.g72.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nl.tudelft.oopp.g72.MainApp;
import nl.tudelft.oopp.g72.localvariables.LocalVariables;

public class AssistantController implements Initializable {

    @FXML
    Label studentCode;
    @FXML
    Label studentCount;

    /**
     * When starting up it will show the student code and the studentCount.
     * @param location url location
     * @param arg1 arg 1
     *
     */
    public void initialize(URL location, ResourceBundle arg1) {
        studentCode.setText(LocalVariables.joinStudent);
        try {
            studentCount.setText(String.valueOf(amountParticipants()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Will go to the modcode window and display the mod code.
     * @throws IOException
     *
     */
    public void moderatorCode() throws IOException {
        System.out.println(LocalVariables.joinModerator);
        Stage dia = new Stage();
        dia.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/modCode_view.fxml"))));

        dia.initModality(Modality.APPLICATION_MODAL);
        dia.requestFocus();
        dia.showAndWait();
    }


    public void teacherView() throws IOException {
        MainApp.window.setScene(new Scene(
                FXMLLoader.load(getClass().getResource("/fxml/teacher_view.fxml"))));
    }

    /**
     * Method that will return the amount of participants in a room.
     * @return returns long of participants
     * @throws IOException exception
     * @throws InterruptedException exception
     *
     */
    public long amountParticipants() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create("http://localhost:8080/api/v1/participants"))
                .header("Token", LocalVariables.token)
                .header("RoomId", String.valueOf(LocalVariables.roomId))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response.body());
        LocalVariables.participantsAmount = node.size();
        return LocalVariables.participantsAmount;
    }

    public void quit() throws IOException {
        MainApp.window.setScene(new Scene(
                FXMLLoader.load(getClass().getResource("/fxml/login.fxml"))));
    }
}
