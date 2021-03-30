package nl.tudelft.oopp.g72.controllers;

import static nl.tudelft.oopp.g72.localvariables.LocalVariables.sortedQuestions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import nl.tudelft.oopp.g72.MainApp;
import nl.tudelft.oopp.g72.entities.Question;
import nl.tudelft.oopp.g72.entities.QuestionListCell;
import nl.tudelft.oopp.g72.entities.QuestionListSelectionModel;
import nl.tudelft.oopp.g72.localvariables.LocalVariables;


public class AssistantController implements Initializable {

    @FXML
    private Label lectureName;
    @FXML
    Label studentCount;
    @FXML
    private ListView<Question> listView;

    /**
     * When starting up it will show the student code and the studentCount.
     * @param location url location
     * @param arg1 arg 1
     *
     */
    public void initialize(URL location, ResourceBundle arg1) {
        listView.setItems(sortedQuestions);
        listView.setCellFactory(lw -> new QuestionListCell());
        listView.setSelectionModel(new QuestionListSelectionModel<>());
        listView.setFocusTraversable(false);
        try {
            studentCount.setText(String.valueOf(amountParticipants()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    /**
     * Executed when 'info' button is clicked.
     */
    public void info() {

    }

    /**
     * Executed when 'merge' button is clicked.
     */
    public void merge() {

    }

    /**
     * Executed when 'edit' button is clicked.
     */
    public void edit() {

    }

    /**
     * Executed when 'remove' button is clicked.
     */
    public void remove() {

    }

}
