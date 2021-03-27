package nl.tudelft.oopp.g72.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nl.tudelft.oopp.g72.localvariables.LocalVariables;

import java.io.IOException;


public class AssistantController {

    @FXML
    Label studentCode;


    public void moderatorCode() throws IOException {
        System.out.println(LocalVariables.moderatorCode);
        Stage dia = new Stage();
        dia.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/modCode_view.fxml"))));
        dia.initModality(Modality.APPLICATION_MODAL);
        dia.requestFocus();
        dia.showAndWait();
    }


}
