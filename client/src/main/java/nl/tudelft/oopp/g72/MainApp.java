package nl.tudelft.oopp.g72;

import static nl.tudelft.oopp.g72.localvariables.LocalVariables.stompSession;
import static nl.tudelft.oopp.g72.localvariables.LocalVariables.webSocketMadness;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.tudelft.oopp.g72.localvariables.LocalVariables;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * The main class of the client side. From here the window is launched and the connection with
 * the server side is made.
 */
public class MainApp extends Application {
    /**
     * The stage in which the application is shown to the user.
     */
    public static Stage window;

    /**
     * The main method, launches JavaFX by calling the launch method.
     *
     * @param args the default args variable.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The JavaFX start method. Called by the previous launch method found in the main method.
     * Here the websockets get started, the JavaFX login template gets loaded onto the scene
     * and the scene gets set onto the stage (and thestage is shown).
     *
     * @param primaryStage The main stage which is opened when the class is run.
     * @throws Exception exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        webSocketMadness = new WebSocketMadness();

        ListenableFuture<StompSession> f = webSocketMadness.connect();
        stompSession = f.get();

        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        primaryStage.setTitle("AskNow");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Executed when the stage gets closed. Makes sure the server gets closed properly.
     */
    @Override
    public void stop() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create("http://localhost:8080/api/v1/participants/leave"))
                .header("Token", LocalVariables.token)
                .build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
