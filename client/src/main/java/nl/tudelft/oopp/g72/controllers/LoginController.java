package nl.tudelft.oopp.g72.controllers;

import static nl.tudelft.oopp.g72.localvariables.LocalVariables.joinModerator;
import static nl.tudelft.oopp.g72.localvariables.LocalVariables.joinStudent;
import static nl.tudelft.oopp.g72.localvariables.LocalVariables.lectureName;
import static nl.tudelft.oopp.g72.localvariables.LocalVariables.open;
import static nl.tudelft.oopp.g72.localvariables.LocalVariables.roomId;
import static nl.tudelft.oopp.g72.localvariables.LocalVariables.scheduledTime;
import static nl.tudelft.oopp.g72.localvariables.LocalVariables.stompSession;
import static nl.tudelft.oopp.g72.localvariables.LocalVariables.webSocketMadness;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nl.tudelft.oopp.g72.MainApp;
import nl.tudelft.oopp.g72.localvariables.LocalVariables;

/**
 * Holds the functionality of the login template.
 */
public class LoginController {

    @FXML
    private TextField displayName;
    @FXML
    private TextField roomCode;
    @FXML
    private Label studentCode;


    /**
     * Checks the contents of all fields when attempting to login. When there's no
     * username or when the server doesn't respond accordingly, the login gets blocked.
     *
     * @return Returns boolean value allowing the user to login when true and not when false.
     * @throws IOException IOException
     * @throws InterruptedException InterruptedException
     */
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

        request = HttpRequest.newBuilder(
                URI.create("http://localhost:8080/api/v1/getId/"))
                .header("Token", LocalVariables.token)
                .build();
        response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        LocalVariables.userId = Long.parseLong(response.body());

        return true;
    }

    /**
     * Executed when the 'join room' button is clicked. Checks whether a join code
     * has been filled. If not, it does nothing. If there is it checks whether there's
     * a room matching the code. If there is the room is joined. If not the user is
     * informed about this. When the lecture exists the status of the lecture is loaded.
     * This can be open, closed or scheduled.
     */
    public void joinRoom() throws IOException, InterruptedException {
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

        System.out.println(response.body());

        char first = '{';
        if (response.body().charAt(0) == first) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response.body());
            roomId = node.get("id").asLong();
            lectureName = node.get("name").asText();
            scheduledTime = node.get("scheduledTime").asLong();
            joinStudent = node.get("joincodeStudent").asText();
            joinModerator = node.get("joincodeModerator").asText();

            webSocketMadness.subscribe(stompSession);

            MainApp.window.setScene(new Scene(
                       FXMLLoader.load(getClass().getResource("/fxml/assistant_view.fxml"))));

        } else {
            HttpClient client2 = HttpClient.newHttpClient();
            HttpRequest request2 = HttpRequest.newBuilder(
                    URI.create("http://localhost:8080/api/v1/open"))
                    .header("Code", roomCode.getText())
                    .build();
            HttpResponse<String> response2 = client2.send(
                    request2, HttpResponse.BodyHandlers.ofString());
            long open = Integer.parseInt(response2.body());
            System.out.println(open);
            if (open == -1) {
                Alert alert = new Alert(
                        Alert.AlertType.ERROR, "The lecture has already been closed");
                alert.show();
            } else if (open == 0) {
                roomId = Long.valueOf(response.body());

                HttpRequest request3 = HttpRequest.newBuilder(
                        URI.create("http://localhost:8080/api/v1/info"))
                        .header("RoomId", String.valueOf(roomId))
                        .build();
                response = client.send(request3, HttpResponse.BodyHandlers.ofString());
                String text = response.body();
                int idx = text.indexOf(";");
                joinStudent = text.substring(0, idx);
                lectureName = text.substring(idx + 1);

                webSocketMadness.subscribe(stompSession);

                MainApp.window.setScene(new Scene(
                        FXMLLoader.load(getClass().getResource("/fxml/student_view.fxml"))));
            } else {
                String text = "The lecture starts at ";

                Date date = new java.util.Date(open * 1000L);
                SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm");
                String formattedDate = sdf.format(date);

                text += formattedDate;
                text += "!";

                Alert alert = new Alert(
                        Alert.AlertType.ERROR, text);
                alert.show();
            }
        }

    }


    /**
     * Executed when the 'create room' button is clicked. Opens the room creator template
     * and lets the user create a room. Once the room has been created the room is entered
     * in assistant view.
     */
    public void createRoom() throws IOException, InterruptedException {
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

        if (lectureName != null) {
            webSocketMadness.subscribe(stompSession);

            MainApp.window.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("/fxml/assistant_view.fxml"))));
        }
    }
}
