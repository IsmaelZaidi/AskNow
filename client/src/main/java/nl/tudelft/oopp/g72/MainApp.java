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

public class MainApp extends Application {
    public static Stage window;

    public static void main(String[] args) {
        launch(args);
    }

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

    @Override
    public void stop() {
        if (LocalVariables.token != null) {
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
}
