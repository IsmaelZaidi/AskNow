package nl.tudelft.oopp.g72.entities;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nl.tudelft.oopp.g72.localvariables.LocalVariables;

/**
 * Class holding functionality regarding QuestionListCellTeacher.
 */
public class QuestionListCellTeacher extends ListCell<Question> {
    private HBox content;
    private Label name;
    private Label upvotes;
    private TextArea text;
    private Button unanswered;
    private AnchorPane anchorPane;

    /**
     * Creates a question cell. It loads the question cell assistant template and adds it
     * as a child to the comment field. It is also sent to the server and spread across users.
     */
    public QuestionListCellTeacher() {
        super();
        try {
            content = FXMLLoader.load(getClass().getResource("/fxml/question_cell_teacher.fxml"));
            VBox vb = (VBox) content.getChildren().get(0);
            anchorPane = (AnchorPane) content.getChildren().get(1);
            name = (Label) vb.getChildren().get(1);
            upvotes = (Label) vb.getChildren().get(2);
            text = (TextArea) anchorPane.getChildren().get(0);
            unanswered = (Button) anchorPane.getChildren().get(1);
            unanswered.setOnMouseClicked(e -> {
                if (unanswered.getText().equals("Unanswered")) {
                    unanswered.setText("Answered");
                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder(
                            URI.create("http://localhost:8080/api/v1/answer/"
                                    + LocalVariables.sortedQuestions.get(getIndex()).getId()
                                    + "/"
                                    + LocalVariables.token
                                    + "/"
                                    + LocalVariables.roomId))
                            .build();
                    try {
                        client.send(request, HttpResponse.BodyHandlers.ofString());
                    } catch (IOException | InterruptedException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            content = new HBox();
        }
    }

    /**
     * Updates the question by filling all fields and adding buttons.
     *
     * @param item a question item
     * @param empty boolean value stating whether empty or not
     */
    @Override
    protected void updateItem(Question item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            name.setText(item.getUser().getNick());
            upvotes.setText(Long.toString(item.getUpvotes()));
            text.setText(item.getText());
            if (item.isAnswered()) {
                unanswered.setText("Answered");
            } else {
                unanswered.setText("Unanswered");
            }
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }
}