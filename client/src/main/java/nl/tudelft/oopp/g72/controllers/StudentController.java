package nl.tudelft.oopp.g72.controllers;

import static nl.tudelft.oopp.g72.localvariables.LocalVariables.filteredQuestions;
import static nl.tudelft.oopp.g72.localvariables.LocalVariables.roomId;
import static nl.tudelft.oopp.g72.localvariables.LocalVariables.sortedQuestions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import nl.tudelft.oopp.g72.MainApp;
import nl.tudelft.oopp.g72.entities.Question;
import nl.tudelft.oopp.g72.entities.QuestionListCellStudent;
import nl.tudelft.oopp.g72.entities.QuestionListSelectionModel;
import nl.tudelft.oopp.g72.localvariables.LocalVariables;

public class StudentController {

    @FXML
    private Label lectureName;
    @FXML
    private Label studentCode;
    @FXML
    private Label studentCount;
    @FXML
    private Button hurryUpButton;
    @FXML
    private Button slowDownButton;
    @FXML
    private TextField messageBar;
    @FXML
    ListView<Question> listView;
    @FXML
    Label newest;
    @FXML
    Label upvoted;
    @FXML
    Label all;
    @FXML
    Label answered;
    @FXML
    Label unanswered;

    int sort = 0;
    int filter = 0;

    @FXML
    void initialize() {
        studentCode.setText(LocalVariables.joinStudent);
        listView.setItems(sortedQuestions);
        listView.setCellFactory(lw -> new QuestionListCellStudent());
        listView.setSelectionModel(new QuestionListSelectionModel<>());
        listView.setFocusTraversable(false);
        try {
            studentCount.setText(String.valueOf(amountParticipants()));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
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

    /**
     * Executed when 'send' button is clicked. Prints text in message bar.
     */
    public void sendMessage() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create("http://localhost:8080/api/v1/ask/"))
                .header("Token", LocalVariables.token)
                .header("RoomId", String.valueOf(roomId))
                .POST(HttpRequest.BodyPublishers.ofString(messageBar.getText()))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(messageBar.getText());
        messageBar.clear();
    }

    /**
     * Executed every time a key is pressed. Checks if the key is 'enter',
     * if so it consumes the enter and calls the 'sendMessage' method.
     * @param event holds the key that's pressed.
     */
    public void enterPressed(KeyEvent event) throws IOException, InterruptedException {
        if (event.getCode() == KeyCode.ENTER) {
            event.consume();
            sendMessage();
        }
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
     * Executed when 'upvote' button is clicked.
     */
    public void upvote() {

    }

    /**
     * Executed when 'remove' button is clicked.
     */
    public void remove() {

    }

    /**
     * Executed when 'quit' button is clicked.
     */
    public void quit() throws IOException {
        MainApp.window.setScene(new Scene(
                FXMLLoader.load(getClass().getResource("/fxml/login.fxml"))));
    }

    void sort(Label newLabel) {
        Label oldLabel;
        if (sort == 0) {
            oldLabel = newest;
        } else {
            oldLabel = upvoted;
        }

        if (oldLabel.equals(newLabel)) {
            return;
        }

        Font aux = oldLabel.getFont();
        oldLabel.setFont(newLabel.getFont());
        newLabel.setFont(aux);

        sortedQuestions.setComparator((o2, o1) -> {
            if (newLabel.getText().equals("new")) {
                sort = 0;
                return Long.compare(o1.getTimestamp(), o2.getTimestamp());
            } else {
                sort = 1;
                return Long.compare(o1.getUpvotes(), o2.getUpvotes());
            }
        });
    }

    public void sortNew(MouseEvent mouseEvent) {
        sort(newest);
    }

    public void sortUpvotes(MouseEvent mouseEvent) {
        sort(upvoted);
    }

    void filter(Label newLabel) {
        Label oldLabel;
        switch (filter) {
            case 0: oldLabel = all;
                break;
            case 1: oldLabel = answered;
                break;
            default: oldLabel = unanswered;
        }

        if (oldLabel.equals(newLabel)) {
            return;
        }

        Font aux = oldLabel.getFont();
        oldLabel.setFont(newLabel.getFont());
        newLabel.setFont(aux);

        if (newLabel.getText().equals("all")) {
            filter = 0;
            filteredQuestions.setPredicate(question -> true);
        } else if (newLabel.getText().equals("answered")) {
            filter = 1;
            filteredQuestions.setPredicate(Question::isAnswered);
        } else {
            filter = 2;
            filteredQuestions.setPredicate(question -> !question.isAnswered());
        }
    }

    public void filterAll(MouseEvent mouseEvent) {
        filter(all);
    }

    public void filterAnswered(MouseEvent mouseEvent) {
        filter(answered);
    }

    public void filterUnanswered(MouseEvent mouseEvent) {
        filter(unanswered);
    }
}
