package nl.tudelft.oopp.g72.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nl.tudelft.oopp.g72.MainApp;
import nl.tudelft.oopp.g72.localvariables.LocalVariables;
import org.apache.tomcat.jni.Local;


public class TeacherController implements Initializable {

    @FXML
    private Label studentCount;
    @FXML
    private Button hurryUpButton;
    @FXML
    private Button slowDownButton;
    @FXML
    private Label stuCode;

    public void initialize(URL location, ResourceBundle arg1) {
        stuCode.setText(LocalVariables.joinStudent);
        try {
            studentCount.setText(String.valueOf(amountParticipants()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Will open the modcode window and display the mod Code.
     * @throws IOException
     *
     */
    public void modCode() throws IOException {
        System.out.println(LocalVariables.joinModerator);
        Stage dia = new Stage();
        dia.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/modCode_view.fxml"))));

        dia.initModality(Modality.APPLICATION_MODAL);
        dia.requestFocus();
        dia.showAndWait();
    }

    /**
     * Executed when 'hurry up' button is clicked. Increments value by one.
     */
    public void hurryUp() {
        hurryUpButton.setText(String.valueOf(Integer.parseInt(hurryUpButton.getText()) + 1));
    }

    /**
     * Executed when 'slow down' button is clicked. Increments value by one.
     */
    public void slowDown() {
        slowDownButton.setText(String.valueOf(Integer.parseInt(slowDownButton.getText()) + 1));
    }

    /**
     * Executed when 'assistant's view' button is clicked.
     */
    public void assistantView() throws IOException {
        MainApp.window.setScene(new Scene(
                FXMLLoader.load(getClass().getResource("/fxml/assistant_view.fxml"))));

    }

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

    /**
     * Executed when 'remove' button is clicked.
     */
    public void remove() {

    }


    /**
     * Executed when 'quit' button is clicked.
     */
    public void quit() {

    }

}