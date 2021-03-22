package nl.tudelft.oopp.g72.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class StudentController {

    @FXML // THIS SHOULD NOT BE A BUTTON BUT PLAIN TEXT
    private Button studentCount;
    @FXML
    private Button hurryUpButton;
    @FXML
    private Button slowDownButton;
    @FXML
    private TextField messageBar;

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
     * Executed when 'quit' button is clicked.
     */
    public void quit() {
        System.out.println(studentCount.getText());
    }
}
