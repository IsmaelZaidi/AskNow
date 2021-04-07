package nl.tudelft.oopp.g72.controllers;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import nl.tudelft.oopp.g72.localvariables.LocalVariables;
import org.apache.tomcat.jni.Local;


public class ModViewController implements Initializable {

    @FXML
    private Label studCode;
    @FXML
    private Label modCode;

    public void initialize(URL location, ResourceBundle arg1) {
        studCode.setText(LocalVariables.joinStudent);
        modCode.setText(LocalVariables.joinModerator);
    }

    public void close(ActionEvent e) {
        Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
        s.close();
    }

    /**
     * Method to save the student code to clipboard.
     * @param e
     */
    public void saveToClipStudent(ActionEvent e) {
        String studentCode = LocalVariables.joinStudent;
        StringSelection selection = new StringSelection(studentCode);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }

    /**
     * Method to save the moderator code to clipboard.
     * @param e
     */
    public void saveToClipModerator(ActionEvent e) {
        String moderatorCode = LocalVariables.joinModerator;
        StringSelection selection = new StringSelection(moderatorCode);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }
}
