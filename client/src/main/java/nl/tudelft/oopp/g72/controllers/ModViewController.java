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




public class ModViewController implements Initializable {

    @FXML
    private Label modCode;

    public void initialize(URL location, ResourceBundle arg1) {
        modCode.setText(LocalVariables.joinModerator);
    }

    public void close(ActionEvent e) {
        Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
        s.close();
    }

    /**
     * Method to save the mod code to clipboard.
     * @param e
     *
     */
    public void saveToClip(ActionEvent e) {
        String moderatorCode = LocalVariables.joinModerator;
        StringSelection selection = new StringSelection(moderatorCode);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }
}
