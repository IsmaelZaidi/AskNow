package nl.tudelft.oopp.g72.entities;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Class holding functionality regarding QuestionListCell.
 */
public class QuestionListCell extends ListCell<Question> {
    private HBox content;
    private Label name;
    private Label upvotes;
    private TextArea text;

    /**
     * Creates a question cell. It loads the question cell template and adds it as a child
     * to the comment field.
     */
    public QuestionListCell() {
        super();
        try {
            content = FXMLLoader.load(getClass().getResource("/fxml/question_cell.fxml"));
            VBox vb = (VBox) content.getChildren().get(0);
            name = (Label) vb.getChildren().get(1);
            upvotes = (Label) vb.getChildren().get(2);
            text = (TextArea) content.getChildren().get(1);
        } catch (Exception e) {
            content = new HBox();
        }
    }

    /**
     * Method updating a question.
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
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }
}
