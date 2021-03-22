package nl.tudelft.oopp.g72;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/student_view.fxml"));
        primaryStage.setTitle("Insert application name");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
