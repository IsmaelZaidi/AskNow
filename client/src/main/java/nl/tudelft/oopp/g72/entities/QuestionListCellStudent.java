package nl.tudelft.oopp.g72.entities;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nl.tudelft.oopp.g72.localvariables.LocalVariables;

/**
 * Class holding functionality regarding QuestionListCellStudent.
 */
public class QuestionListCellStudent extends ListCell<Question> {
    private HBox content;
    private Label name;
    private Label upvotes;
    private Button addUpvote;
    private TextArea text;
    private Button answer;
    private Button remove;
    private AnchorPane anchorPane;

    /**
     * Creates a question cell. It loads the question cell assistant template and adds it
     * as a child to the comment field. It is also sent to the server and spread across users.
     */
    public QuestionListCellStudent() {
        super();
        try {
            content = FXMLLoader.load(getClass().getResource("/fxml/question_cell_student.fxml"));
            VBox vb = (VBox) content.getChildren().get(0);
            anchorPane = (AnchorPane) content.getChildren().get(1);
            name = (Label) vb.getChildren().get(1);
            upvotes = (Label) vb.getChildren().get(2);
            addUpvote = (Button) vb.getChildren().get(3);
            addUpvote.setOnMouseClicked(e -> {
                Question question = LocalVariables.sortedQuestions.get(getIndex());
                for (int i = 0; i < LocalVariables.questions.size(); i++) {
                    if (LocalVariables.questions.get(i).getId() == question.getId()) {
                        question = LocalVariables.questions.get(i);

                        HttpClient client = HttpClient.newHttpClient();
                        HttpRequest request = HttpRequest.newBuilder(
                                URI.create("http://localhost:8080/api/v1/upvote/" + question.getId() + "/" + LocalVariables.token + "/" + LocalVariables.roomId))
                                .build();
                        try {
                            client.send(request, HttpResponse.BodyHandlers.ofString());
                        } catch (IOException | InterruptedException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
            });
            text = (TextArea) anchorPane.getChildren().get(0);
            remove = (Button) anchorPane.getChildren().get(1);
            answer = (Button) anchorPane.getChildren().get(2);
            remove.setOnMouseClicked(e -> {
                Question question = LocalVariables.sortedQuestions.get(getIndex());
                if (question.getUser().getId() != LocalVariables.userId) {
                    return;
                }
                for (int i = 0; i < LocalVariables.questions.size(); i++) {
                    if (LocalVariables.questions.get(i).getId() == question.getId()) {
                        question = LocalVariables.questions.get(i);
                        question.setText("Removed.");
                        LocalVariables.questions.set(i, question);

                        HttpClient client = HttpClient.newHttpClient();
                        HttpRequest request = HttpRequest.newBuilder(
                                URI.create("http://localhost:8080/api/v1/question/" + question.getId() + "/" + LocalVariables.roomId))
                                .DELETE()
                                .header("Token", LocalVariables.token)
                                .build();
                        try {
                            client.send(request, HttpResponse.BodyHandlers.ofString());
                        } catch (IOException | InterruptedException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
            });
            answer.setOnMouseClicked(e -> {
                if (answer.getText().equals("Answered")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Answer");
                    alert.setHeaderText(null);
                    alert.setContentText(LocalVariables.sortedQuestions.get(getIndex()).getAnswer());
                    alert.showAndWait();
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
            if (item.getUser().getId() == LocalVariables.userId) {
                remove.setText("Remove");
            } else {
                remove.setText("");
            }
            if (item.isAnswered()) {
                answer.setText("Answered");
            } else {
                answer.setText("Unanswered");
            }
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }
}