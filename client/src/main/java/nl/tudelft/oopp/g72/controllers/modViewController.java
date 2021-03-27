package nl.tudelft.oopp.g72.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import nl.tudelft.oopp.g72.localvariables.LocalVariables;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;


public class modViewController {




    @FXML
    private Label modCode;

    public void close(ActionEvent e) {
        Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
        s.close();
    }

    public void saveToClip(ActionEvent e) {
        String moderatorCode = LocalVariables.moderatorCode;
        StringSelection selection = new StringSelection(moderatorCode);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }
}
