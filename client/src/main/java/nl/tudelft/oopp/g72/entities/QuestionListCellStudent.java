package nl.tudelft.oopp.g72.entities;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import nl.tudelft.oopp.g72.localvariables.LocalVariables;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class QuestionListCellStudent extends ListCell<Question> {
    private HBox content;
    private Label name;
    private Label upvotes;
    private TextArea text;
    private Button edit;
    private Button remove;
    private AnchorPane anchorPane;

    /**
     * Creates a question cell.
     */
    public QuestionListCellStudent() {
        super();
        try {
            content = FXMLLoader.load(getClass().getResource("/fxml/question_cell_student.fxml"));
            VBox vb = (VBox) content.getChildren().get(0);
            anchorPane = (AnchorPane) content.getChildren().get(1);
            name = (Label) vb.getChildren().get(1);
            upvotes = (Label) vb.getChildren().get(2);
            text = (TextArea) anchorPane.getChildren().get(0);
            remove = (Button) anchorPane.getChildren().get(1);
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
        } catch (Exception e) {
            content = new HBox();
        }
    }

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
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }
}