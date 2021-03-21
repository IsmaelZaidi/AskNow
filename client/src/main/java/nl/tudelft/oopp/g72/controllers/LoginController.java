package nl.tudelft.oopp.g72.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML // Value in display name input field.
    private TextField displayName;
    @FXML // Value in room code input field.
    private TextField roomCode;


    /**
     * Executed when the 'join room' button is clicked.
     */
    public void joinRoom() {
        // Will be executed when 'join' button is clicked.
        System.out.println("Displayname: " + displayName.getText());
        System.out.println("Room code: " + roomCode.getText());
    }

    /**
     * Executed when the 'create room' button is clicked.
     */
    public void createRoom() {
        // Will be executed when 'create room' button is clicked.
    }
}
