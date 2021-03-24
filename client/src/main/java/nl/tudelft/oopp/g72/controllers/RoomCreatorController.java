package nl.tudelft.oopp.g72.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nl.tudelft.oopp.g72.entities.TimeSpinner;

public class RoomCreatorController {
    @FXML
    TextField roomName;

    @FXML
    TimeSpinner scheduledTime;

    @FXML
    public void cancel(ActionEvent e) {
        Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
        s.close();
    }

    /**
     * Executes upon clicking the create button on the dialogue box.
     * @param e action event
     */
    @FXML
    public void create(ActionEvent e) {
        if (roomName.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "You haven't filled in a room name!");
            alert.show();
            return;
        }

        LocalTime time = scheduledTime.getValue();
        long epoch = time.toEpochSecond(LocalDate.now(), OffsetDateTime.now().getOffset());

        // http code here
    }
}
