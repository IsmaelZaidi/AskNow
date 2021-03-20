package nl.tudelft.oopp.g72.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField displayName;
    @FXML
    private TextField roomCode;

    public void joinRoom() {
        // Will be executed when 'join' button is clicked.
        System.out.println("Displayname: " + displayName.getText());
        System.out.println("Room code: " + roomCode.getText());
    }

    public void createRoom() {
        // Will be executed when 'create room' button is clicked.
    }
}
