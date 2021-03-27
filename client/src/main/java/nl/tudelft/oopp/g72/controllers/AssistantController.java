package nl.tudelft.oopp.g72.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nl.tudelft.oopp.g72.MainApp;
import nl.tudelft.oopp.g72.localvariables.LocalVariables;

public class AssistantController implements Initializable {

    @FXML
    Label studentCode;


    public void initialize(URL location, ResourceBundle arg1) {
        studentCode.setText(LocalVariables.studentCode);
    }

    /**
     * Will go to the modcode window and display the mod code.
     * @throws IOException
     *
     */
    public void moderatorCode() throws IOException {
        System.out.println(LocalVariables.moderatorCode);
        Stage dia = new Stage();
        dia.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/modCode_view.fxml"))));

        dia.initModality(Modality.APPLICATION_MODAL);
        dia.requestFocus();
        dia.showAndWait();
    }


    public void teacherView() throws IOException {
        MainApp.window.setScene(new Scene(
                FXMLLoader.load(getClass().getResource("/fxml/teacher_view.fxml"))));
    }
}
