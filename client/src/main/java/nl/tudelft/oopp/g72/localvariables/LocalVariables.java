package nl.tudelft.oopp.g72.localvariables;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import nl.tudelft.oopp.g72.WebSocketMadness;
import nl.tudelft.oopp.g72.entities.Question;
import org.springframework.messaging.simp.stomp.StompSession;

/**
 * Class holding the local variables.
 */
public class LocalVariables {
    public static String token;
    public static long userId;

    public static Long roomId;
    public static String lectureName;
    public static boolean open;
    public static long scheduledTime;
    public static String joinStudent;
    public static String joinModerator;
    public static long participantsAmount;

    public static ObservableList<Question> questions = FXCollections.observableArrayList();
    public static FilteredList<Question> filteredQuestions = new FilteredList<>(questions);
    public static SortedList<Question> sortedQuestions = new SortedList<>(filteredQuestions,
        (o2, o1) -> Long.compare(o1.getTimestamp(), o2.getTimestamp()));

    public static WebSocketMadness webSocketMadness;
    public static StompSession stompSession;
}