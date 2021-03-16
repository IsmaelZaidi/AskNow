package nl.tudelft.oopp.g72.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RoomController {

    public static void joinRoom(String code, String token) throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();

        // create a request
        var request = HttpRequest.newBuilder(
                URI.create("https://localhost:8080/api/v1/join/"+code+"/"+token))
                .header("accept", "application/json")
                .build();

        // use the client to send the request
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response);
    }
}
