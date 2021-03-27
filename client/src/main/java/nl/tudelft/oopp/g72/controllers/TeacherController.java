package nl.tudelft.oopp.g72.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nl.tudelft.oopp.g72.MainApp;
import nl.tudelft.oopp.g72.localvariables.LocalVariables;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherController {

    @FXML
    private Label studentCount;
    @FXML
    private Button hurryUpButton;
    @FXML
    private Button slowDownButton;
    @FXML
    private Label studentCode;


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
    public void assistantView() throws IOException {
        MainApp.window.setScene(new Scene(
                FXMLLoader.load(getClass().getResource("/fxml/assistant_view.fxml"))));

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

    public void modCode() {
    }
}