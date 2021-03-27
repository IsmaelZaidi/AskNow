package nl.tudelft.oopp.g72.controllers;

import static nl.tudelft.oopp.g72.localvariables.LocalVariables.questions;
import static nl.tudelft.oopp.g72.localvariables.LocalVariables.sortedQuestions;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import nl.tudelft.oopp.g72.entities.Question;
import nl.tudelft.oopp.g72.entities.QuestionListCell;
import nl.tudelft.oopp.g72.entities.QuestionListSelectionModel;
import nl.tudelft.oopp.g72.entities.User;

public class StudentController {

    @FXML
    private Label studentCount;
    @FXML
    private Button hurryUpButton;
    @FXML
    private Button slowDownButton;
    @FXML
    private TextField messageBar;
    @FXML
    ListView<Question> listView;

    @FXML
    void initialize() {
        User user1 = new User(1, "john");
        User user2 = new User(2, "cena");
        questions.addAll(new Question(user1, "What is going on here?", 12, 5),
                new Question(user2, "Do you have a 3080 at MSRP?", -999, 0),
                new Question(user2, "Do you have asdasdasdasdsa 3080 at MSRP?", -999, 10));

        listView.setItems(sortedQuestions);
        listView.setCellFactory(lw -> new QuestionListCell());
        listView.setSelectionModel(new QuestionListSelectionModel<>());
        listView.setFocusTraversable(false);
    }

    /**
     * Executed when 'send' button is clicked. Prints text in message bar.
     */
    public void sendMessage() {
        System.out.println(messageBar.getText());
        messageBar.clear();
    }

    /**
     * Executed every time a key is pressed. Checks if the key is 'enter',
     * if so it consumes the enter and calls the 'sendMessage' method.
     * @param event holds the key that's pressed.
     */
    public void enterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            event.consume();
            sendMessage();
        }
    }

    /**
     * Executed when 'hurry up' button is clicked. Increments value by one.
     */
    public void hurryUp() {
        hurryUpButton.setText(String.valueOf(Integer.parseInt(hurryUpButton.getText()) + 1));
    }

    /**
     * Executed when 'slow down' button is clicked. Increments value by one.
     */
    public void slowDown() {
        slowDownButton.setText(String.valueOf(Integer.parseInt(slowDownButton.getText()) + 1));
    }

    /**
     * Executed when 'upvote' button is clicked.
     */
    public void upvote() {

    }

    /**
     * Executed when 'edit' button is clicked.
     */
    public void edit() {

    }

    /**
     * Executed when 'remove' button is clicked.
     */
    public void remove() {

    }

    /**
     * Executed when 'quit' button is clicked.
     */
    public void quit() {

    }
}
