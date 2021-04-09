package nl.tudelft.oopp.g72.controllers;

import static nl.tudelft.oopp.g72.localvariables.LocalVariables.filteredQuestions;
import static nl.tudelft.oopp.g72.localvariables.LocalVariables.joinModerator;
import static nl.tudelft.oopp.g72.localvariables.LocalVariables.joinStudent;
import static nl.tudelft.oopp.g72.localvariables.LocalVariables.roomId;
import static nl.tudelft.oopp.g72.localvariables.LocalVariables.sortedQuestions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nl.tudelft.oopp.g72.MainApp;
import nl.tudelft.oopp.g72.entities.Question;
import nl.tudelft.oopp.g72.entities.QuestionListCellAssistant;
import nl.tudelft.oopp.g72.entities.QuestionListSelectionModel;
import nl.tudelft.oopp.g72.localvariables.LocalVariables;

/**
 * Holds the functionality of the assistant template.
 */
public class AssistantController implements Initializable {

    @FXML
    private Label studentCode;
    @FXML
    private Label lectureName;
    @FXML
    Label studentCount;
    @FXML
    private ListView<Question> listView;
    @FXML
    TextField messageBar;
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
     *
     * @param location url location
     * @param arg1 arg 1
     *
     */
    public void initialize(URL location, ResourceBundle arg1) {
        studentCode.setText(LocalVariables.joinStudent);
        lectureName.setText(LocalVariables.lectureName);
        listView.setItems(sortedQuestions);
        listView.setCellFactory(lw -> new QuestionListCellAssistant());
        listView.setSelectionModel(new QuestionListSelectionModel<>());
        listView.setFocusTraversable(false);
        try {
            studentCount.setText(String.valueOf(amountParticipants()));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * When 'teacherview' button is clicked the stage will switch to the teacherview
     * template.
     *
     * @throws IOException exception
     */
    public void teacherView() throws IOException {
        MainApp.window.setScene(new Scene(
                FXMLLoader.load(getClass().getResource("/fxml/teacher_view.fxml"))));
    }

    /**
     * Method that will return the amount of participants in a room.
     *
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
     * Will open the modcode window and display the mod Code.
     *
     * @throws IOException exception
     */
    public void moderatorCode() throws IOException {
        System.out.println(LocalVariables.joinModerator);
        Stage dia = new Stage();
        dia.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/modCode_view.fxml"))));

        dia.initModality(Modality.APPLICATION_MODAL);
        dia.requestFocus();
        dia.showAndWait();
    }

    /**
     * Executed when 'send' button is clicked. The contents of the message bar are sent to
     * the server and displayed in the comment field. Then the message bar is cleared again.
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
     * Executed every time a key is pressed. Checks if the key is 'enter', if so it
     * consumes the enter and calls the 'sendMessage' method.
     *
     * @param event holds the key that's pressed.
     */
    public void enterPressed(KeyEvent event) throws IOException, InterruptedException {
        if (event.getCode() == KeyCode.ENTER) {
            event.consume();
            sendMessage();
        }
    }

    /**
     * Sorting buttons. When 'newest' is clicked it sorts based on how old the message
     * is. When 'upvotes' is clicked it sorts based on the amount of upvotes.
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

    /**
     * Exports the list of questions as a text file.
     * @param actionEvent event
     */
    public void export(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser
                .ExtensionFilter("Text files", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(MainApp.window);

        if (file != null) {
            try {
                List<Question> questions = LocalVariables.questions;
                questions.sort((o1, o2) -> -Integer.compare(o1.getUpvotes(), o2.getUpvotes()));

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(LocalVariables.lectureName);
                stringBuilder.append(" - Join codes:\n Moderator: ");
                stringBuilder.append(joinModerator);
                stringBuilder.append("\n Student: ");
                stringBuilder.append(joinStudent);
                stringBuilder.append("\n\n");
                for (Question q: questions) {
                    stringBuilder.append(new SimpleDateFormat("HH:mm").format(q.getTimestamp()));
                    stringBuilder.append(" ");
                    stringBuilder.append(q.getUpvotes());
                    stringBuilder.append(" upvotes\n");
                    stringBuilder.append(q.getUser().getNick());
                    stringBuilder.append("\n");
                    stringBuilder.append(q.getText());
                    if (q.isAnswered()) {
                        stringBuilder.append("\nAnswered\n");
                        if (q.getAnswer().equals("")) {
                            stringBuilder.append("No text answer given\n");
                        }
                    } else {
                        stringBuilder.append("\nUnanswered\n");
                    }
                    stringBuilder.append("\n\n");
                }

                PrintWriter writer;
                writer = new PrintWriter(file);
                writer.println(stringBuilder);
                writer.close();
            } catch (IOException ex) {
                System.out.println("couldn't save file");
            }
        }
    }
}
