package nl.tudelft.oopp.g72;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application implements EventHandler<ActionEvent> {
    public static void main(String[] args) {
        launch(args);
    }

    Button button1;
    Button button2;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Title placeholder");

        button1 = new Button("Click me");
        // We should avoid using this, this takes up a lot of code.
        // This refers to the current class and looks for the handle event to find it's action.
        button1.setOnAction(this);
        button2 = new Button("Or me");
        // We should use lambda functions to make code more clean and efficient.
        button2.setOnAction(e -> System.out.println("This is button two."));

        VBox layout = new VBox(20);
        layout.getChildren().addAll(button1, button2);
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == button1) {
            System.out.println("This is button one.");
        }
    }
}
