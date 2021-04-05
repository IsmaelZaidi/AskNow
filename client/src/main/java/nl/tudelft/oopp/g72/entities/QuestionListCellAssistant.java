package nl.tudelft.oopp.g72.entities;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import nl.tudelft.oopp.g72.localvariables.LocalVariables;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import org.apache.tomcat.jni.Local;
import org.springframework.web.context.request.AsyncWebRequestInterceptor;

public class QuestionListCellAssistant extends ListCell<Question> {
    private HBox content;
    private Label name;
    private Label upvotes;
    private TextArea text;
    private Button remove, edit;
    private AnchorPane anchorPane;

    /**
     * Creates a question cell.
     */
    public QuestionListCellAssistant() {
        super();
        try {
            content = FXMLLoader.load(getClass().getResource("/fxml/question_cell_assistant.fxml"));
            VBox vb = (VBox) content.getChildren().get(0);
            anchorPane = (AnchorPane) content.getChildren().get(1);
            name = (Label) vb.getChildren().get(1);
            upvotes = (Label) vb.getChildren().get(2);
            text = (TextArea) anchorPane.getChildren().get(0);
            edit = (Button) anchorPane.getChildren().get(1);
            edit.setOnMouseClicked(e -> {
                Question question = LocalVariables.sortedQuestions.get(getIndex());
                for(int i = 0; i < LocalVariables.questions.size(); i++){
                    if(LocalVariables.questions.get(i).getId() == question.getId()){
                        question = LocalVariables.questions.get(i);
                        TextInputDialog td = new TextInputDialog("Enter edited message:");
                        td.showAndWait();
                        question.setText(td.getEditor().getText());
                        LocalVariables.questions.set(i, question);
                    }
                }
            });
            remove = (Button) anchorPane.getChildren().get(2);
            remove.setOnMouseClicked(e -> {
                Question question = LocalVariables.sortedQuestions.get(getIndex());
                for(int i = 0; i < LocalVariables.questions.size(); i++){
                    if(LocalVariables.questions.get(i).getId() == question.getId()){
                        question = LocalVariables.questions.get(i);
                        question.setText("Removed.");
                        LocalVariables.questions.set(i, question);
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
            edit.setText("Edit");
            remove.setText("Remove");
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }
}
