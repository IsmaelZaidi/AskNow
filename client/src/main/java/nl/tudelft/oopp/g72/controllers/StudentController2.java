package nl.tudelft.oopp.g72.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import nl.tudelft.oopp.g72.entities.Question;
import nl.tudelft.oopp.g72.entities.QuestionListCell;
import nl.tudelft.oopp.g72.entities.QuestionListSelectionModel;
import nl.tudelft.oopp.g72.entities.User;

public class StudentController2 {
    @FXML
    ListView<Question> listView;

    @FXML
    void initialize() {
        ObservableList<Question> data = FXCollections.observableArrayList();
        User user1 = new User(1, "john");
        User user2 = new User(2, "cena");
        data.addAll(new Question(user1, "What is going on here?", 12),
                new Question(user2, "Do you have a 3080 at MSRP?", -999));

        listView.getItems().addAll(data);
        listView.setCellFactory(lw -> new QuestionListCell());
        listView.setSelectionModel(new QuestionListSelectionModel<>());
        listView.setFocusTraversable(false);
    }

}
