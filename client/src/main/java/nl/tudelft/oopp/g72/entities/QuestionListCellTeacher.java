package nl.tudelft.oopp.g72.entities;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
     * Creates a question cell. It loads the question cell assistant template and adds it as a child to the comment
     * field. It is also sent to the server and spread across users.
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
                unanswered.setText("Answered");
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
            unanswered.setText("Unanswered");
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }
}