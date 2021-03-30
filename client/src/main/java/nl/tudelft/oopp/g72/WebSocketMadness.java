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
import java.util.Collections;
import java.util.List;
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

        stompSession.subscribe("/room" + roomId, new StompFrameHandler() {

            public Type getPayloadType(StompHeaders stompHeaders) {
                return byte[].class;
            }

            public void handleFrame(StompHeaders stompHeaders, Object o) {
                ObjectMapper mapper = new ObjectMapper();
                Question q;
                try {
                    q = mapper.readValue(new String((byte[]) o), Question.class);
                    LocalVariables.questions.add(q);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static class MyHandler extends StompSessionHandlerAdapter {
        public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
            System.out.println("Connected");
        }
    }

}
