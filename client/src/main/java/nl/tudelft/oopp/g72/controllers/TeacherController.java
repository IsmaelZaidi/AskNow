package nl.tudelft.oopp.g72.controllers;

import static nl.tudelft.oopp.g72.localvariables.LocalVariables.filteredQuestions;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nl.tudelft.oopp.g72.MainApp;
import nl.tudelft.oopp.g72.entities.Question;
import nl.tudelft.oopp.g72.entities.QuestionListCellTeacher;
import nl.tudelft.oopp.g72.entities.QuestionListSelectionModel;
import nl.tudelft.oopp.g72.localvariables.LocalVariables;

/**
 * Holds the functionality of the teacher template.
 */
public class TeacherController implements Initializable {

    @FXML
    private Button end;
    @FXML
    private Label lectureName;
    @FXML
    private Label studentCount;
    @FXML
    private Label stuCode;
    @FXML
    private ListView<Question> listView;
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

    /**
     * When starting up it will show the student code and the studentCount.
     * @param location url location
     * @param arg1 arg 1
     *
     */
    public void initialize(URL location, ResourceBundle arg1) {
        listView.setItems(sortedQuestions);
        lectureName.setText(LocalVariables.lectureName);
        listView.setCellFactory(lw -> new QuestionListCellTeacher());
        listView.setSelectionModel(new QuestionListSelectionModel<>());
        listView.setFocusTraversable(false);
        stuCode.setText(LocalVariables.joinStudent);
        try {
            studentCount.setText(String.valueOf(amountParticipants()));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Will open the modcode window and display the mod Code.
     * @throws IOException exception
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
     * When 'assistantview' button is clicked the stage will switch to the
     * assistantview template.
     *
     * @throws IOException exception
     */
    public void assistantView() throws IOException {
        MainApp.window.setScene(new Scene(
                FXMLLoader.load(getClass().getResource("/fxml/assistant_view.fxml"))));

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
     * Executed when 'end' button is clicked. It leaves the room and sets the status of
     * the room to closed.
     */
    public void quit() throws IOException, InterruptedException {
        if (end.getText().equals("End")) {
            end.setText("Closed");

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(
                    URI.create("http://localhost:8080/api/v1/close"))
                    .header("Code", LocalVariables.joinStudent)
                    .build();
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            System.out.println(response);
        }
    }

    /**
     * Sorting buttons. When 'newest' is clicked it sorts based on how old the message is.
     * When 'upvotes' is clicked it sorts based on the amount of upvotes.
     *
     * @param newLabel Holds the new label which we're going to sort by.
     */
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

    /**
     * When 'newest' clicked it calls method sort with 'newest' as input.
     *
     * @param mouseEvent Mouseclick on text 'newest'.
     */
    public void sortNew(MouseEvent mouseEvent) {
        sort(newest);
    }

    /**
     * When 'upvoted' clicked it calls method sort with 'upvoted' as input.
     *
     * @param mouseEvent Mouseclick on text 'upvoted'.
     */
    public void sortUpvotes(MouseEvent mouseEvent) {
        sort(upvoted);
    }

    /**
     * Filtering buttons. When 'all' is clicked there's no filter. When 'answered' is
     * clicked only answered questions are shown. And when 'unanswered' is clicked only
     * unanswered questions are shown.
     *
     * @param newLabel Holds the new label which we're going to sort by.
     */
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

    /**
     * When 'all' clicked it calls method filter with 'all' as input.
     *
     * @param mouseEvent Mouseclick on text 'all'.
     */
    public void filterAll(MouseEvent mouseEvent) {
        filter(all);
    }

    /**
     * When 'answered' clicked it calls method filter with 'answered' as input.
     *
     * @param mouseEvent Mouseclick on text 'answered'.
     */
    public void filterAnswered(MouseEvent mouseEvent) {
        filter(answered);
    }

    /**
     * When 'unanswered' clicked it calls method filter with 'unanswered' as input.
     *
     * @param mouseEvent Mouseclick on text 'unanswered'.
     */
    public void filterUnanswered(MouseEvent mouseEvent) {
        filter(unanswered);
    }
}