package nl.tudelft.oopp.g72.entities;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;

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
            edit = (Button) anchorPane.getChildren().get(1);
            remove = (Button) anchorPane.getChildren().get(2);
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
            edit.setText("Edit");
            remove.setText("Remove");
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }
}