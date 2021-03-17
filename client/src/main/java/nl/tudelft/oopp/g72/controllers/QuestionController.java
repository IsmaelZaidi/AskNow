package nl.tudelft.oopp.g72.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class QuestionController {
    /**
     * DESCRIPTION TO BE ADDED AND IMPROVED HERE
     *
     * @param questionID ID to identify question being upvoted
     * @param token token to be used in request
     * @throws IOException input output exception
     * @throws InterruptedException interruption exception
     */
    public static void upvoteQuestion(String questionID, String token)
            throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();

        // create a request
        var request = HttpRequest.newBuilder(
                URI.create("https://localhost:8080/api/v1/upvote/" + questionID + "/" + token))
                .header("accept", "application/json")
                .build();

        // use the client to send the request
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ///TO DO
        ///Add answered question to the list
        System.out.println(response);
    }
}
