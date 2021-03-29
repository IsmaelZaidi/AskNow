package nl.tudelft.oopp.g72.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TeacherController {

    @FXML
    private Label lectureName;
    @FXML
    private Label studentCount;
    @FXML
    private Button hurryUpButton;
    @FXML
    private Button slowDownButton;

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
     * Executed when 'assistant's view' button is clicked.
     */
    public void assistantView() {

    }

    /**
     * Executed when 'remove' button is clicked.
     */
    public void remove() {

    }

    /**
     * Executed when 'approve' button is clicked.
     */
    public void approve() {

    }

    /**
     * Executed when 'kick' button is clicked.
     */
    public void kick() {

    }

    /**
     * Executed when 'quit' button is clicked.
     */
    public void end() {

    }
}