package nl.tudelft.oopp.g72.controllers;

import static nl.tudelft.oopp.g72.localvariables.LocalVariables.joinModerator;
import static nl.tudelft.oopp.g72.localvariables.LocalVariables.joinStudent;
import static nl.tudelft.oopp.g72.localvariables.LocalVariables.lectureName;
import static nl.tudelft.oopp.g72.localvariables.LocalVariables.open;
import static nl.tudelft.oopp.g72.localvariables.LocalVariables.roomId;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nl.tudelft.oopp.g72.entities.TimeSpinner;
import nl.tudelft.oopp.g72.localvariables.LocalVariables;

public class RoomCreatorController {
    @FXML
    TextField roomName;

    @FXML
    TimeSpinner scheduledTime;

    @FXML
    Label studentCode;

    @FXML
    public void cancel(ActionEvent e) {
        Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
        s.close();
    }

    /**
     * Executes upon clicking the create button on the dialogue box.
     * @param e action event
     */
    @FXML
    public void create(ActionEvent e) throws IOException, InterruptedException {
        if (roomName.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "You haven't filled in a room name!");
            alert.show();
            return;
        }

        LocalTime time = scheduledTime.getValue();
        long epoch = time.toEpochSecond(LocalDate.now(), OffsetDateTime.now().getOffset());

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create("http://localhost:8080/api/v1/create/"))
                .POST(HttpRequest.BodyPublishers.ofString(roomName.getText()))
                .header("Token", LocalVariables.token)
                .header("ScheduledTime", Long.toString(epoch))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response.body());
        roomId = node.get("id").asLong();
        lectureName = node.get("name").asText();
        open = node.get("open").asBoolean();
        LocalVariables.scheduledTime = node.get("scheduledTime").asLong();
        joinStudent = node.get("joincodeStudent").asText();
        joinModerator = node.get("joincodeModerator").asText();
        Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
        s.close();
    }
}
