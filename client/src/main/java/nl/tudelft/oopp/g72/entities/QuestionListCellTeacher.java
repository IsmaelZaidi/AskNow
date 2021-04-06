package nl.tudelft.oopp.g72.entities;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class QuestionListCellTeacher extends ListCell<Question> {
    private HBox content;
    private Label name;
    private Label upvotes;
    private TextArea text;

    /**
     * Creates a question cell.
     */
    public QuestionListCellTeacher() {
        super();
        try {
            content = FXMLLoader.load(getClass().getResource("/fxml/question_cell_teacher.fxml"));
            VBox vb = (VBox) content.getChildren().get(0);
            name = (Label) vb.getChildren().get(1);
            upvotes = (Label) vb.getChildren().get(2);
            text = (TextArea) content.getChildren().get(1);
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
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }
}