package nl.tudelft.oopp.g72;

import static nl.tudelft.oopp.g72.localvariables.LocalVariables.questions;
import static nl.tudelft.oopp.g72.localvariables.LocalVariables.roomId;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.application.Platform;
import nl.tudelft.oopp.g72.entities.MessageAnswer;
import nl.tudelft.oopp.g72.entities.MessageDelete;
import nl.tudelft.oopp.g72.entities.MessageUpvote;
import nl.tudelft.oopp.g72.entities.Question;
import nl.tudelft.oopp.g72.localvariables.LocalVariables;
import org.apache.tomcat.jni.Local;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import org.springframework.web.socket.sockjs.frame.Jackson2SockJsMessageCodec;

/**
 * Class handling the websockets.
 */
public class WebSocketMadness {

    private static final WebSocketHttpHeaders headers = new WebSocketHttpHeaders();

    /**
     * Connects to a websocket endpoint.
     * @return the session
     */
    public ListenableFuture<StompSession> connect() {

        Transport webSocketTransport = new WebSocketTransport(new StandardWebSocketClient());
        List<Transport> transports = Collections.singletonList(webSocketTransport);

        SockJsClient sockJsClient = new SockJsClient(transports);
        sockJsClient.setMessageCodec(new Jackson2SockJsMessageCodec());

        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);

        String url = "ws://{host}:{port}/stomp";
        return stompClient.connect(url, headers, new MyHandler(), "localhost", 8080);
    }

    /**
     * Subscribe to the room.
     * @param stompSession the session
     */
    public void subscribe(StompSession stompSession) throws IOException, InterruptedException {
        System.out.println("Subscribing to room id " + roomId);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create("http://localhost:8080/api/v1/retrieve/"))
                .header("Token", LocalVariables.token)
                .header("Time", "0")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        List<Question> questions = mapper.readValue(response.body(),
                new TypeReference<List<Question>>(){});
        LocalVariables.questions.addAll(questions);

        stompSession.subscribe("/room" + roomId + "question", new StompFrameHandler() {

            public Type getPayloadType(StompHeaders stompHeaders) {
                return byte[].class;
            }

            public void handleFrame(StompHeaders stompHeaders, Object o) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    Question q;
                    q = mapper.readValue(new String((byte[]) o), Question.class);
                    Platform.runLater(() -> LocalVariables.questions.add(q));
                } catch (JsonProcessingException e1) {
                    e1.printStackTrace();
                }
            }
        });

        stompSession.subscribe("/room" + roomId + "answer", new StompFrameHandler() {

            public Type getPayloadType(StompHeaders stompHeaders) {
                return byte[].class;
            }

            public void handleFrame(StompHeaders stompHeaders, Object o) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    MessageAnswer m;
                    m = mapper.readValue(new String((byte[]) o), MessageAnswer.class);
                    Platform.runLater(() -> {
                        for (Question q : LocalVariables.questions) {
                            if (q.getId() == m.getQuestionId()) {
                                q.setAnswered(true);
                                q.setAnswer(m.getAnswer());
                            }
                        }
                    });
                } catch (JsonProcessingException e2) {
                    e2.printStackTrace();
                }
            }
        });

        stompSession.subscribe("/room" + roomId + "delete", new StompFrameHandler() {

            public Type getPayloadType(StompHeaders stompHeaders) {
                return byte[].class;
            }

            public void handleFrame(StompHeaders stompHeaders, Object o) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    MessageDelete m;
                    m = mapper.readValue(new String((byte[]) o), MessageDelete.class);
                    Platform.runLater(() -> {
                        LocalVariables.questions
                                .removeIf(q -> q.getId() == m.getQuestionId());
                    });
                } catch (JsonProcessingException e3) {
                    e3.printStackTrace();
                }
            }
        });

        stompSession.subscribe("/room" + roomId + "upvote", new StompFrameHandler() {

            public Type getPayloadType(StompHeaders stompHeaders) {
                return byte[].class;
            }

            public void handleFrame(StompHeaders stompHeaders, Object o) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    MessageUpvote m;
                    m = mapper.readValue(new String((byte[]) o), MessageUpvote.class);
                    Platform.runLater(() -> {
                        for (int i = 0; i < LocalVariables.questions.size(); i++) {
                            if (LocalVariables.questions.get(i).getId() == m.getQuestionId()) {
                                Question question = LocalVariables.questions.get(i);
                                question.setUpvotes(m.getUpvotes());
                                LocalVariables.questions.set(i, question);
                            }
                        }
                    });
                } catch (JsonProcessingException e4) {
                    e4.printStackTrace();
                }
            }
        });

        stompSession.subscribe("/room" + roomId + "edit", new StompFrameHandler() {

            public Type getPayloadType(StompHeaders stompHeaders) {
                return byte[].class;
            }

            public void handleFrame(StompHeaders stompHeaders, Object o) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    Question q;
                    q = mapper.readValue(new String((byte[]) o), Question.class);
                    Platform.runLater(() -> {
                        for (int i = 0; i < LocalVariables.questions.size(); i++) {
                            if (LocalVariables.questions.get(i).getId() == q.getId()) {
                                LocalVariables.questions.set(i, q);
                            }
                        }
                    });
                } catch (JsonProcessingException e5) {
                    e5.printStackTrace();
                }
            }
        });
    }

    /**
     * Handler class.
     */
    private static class MyHandler extends StompSessionHandlerAdapter {
        /**
         * Executes after connection is done. Prints 'connected' to notify connection was
         * successful.
         *
         * @param stompSession stompSession
         * @param stompHeaders stompHeaders
         */
        public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
            System.out.println("Connected");
        }
    }

}
