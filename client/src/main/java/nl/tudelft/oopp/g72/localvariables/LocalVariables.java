package nl.tudelft.oopp.g72.localvariables;

import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import nl.tudelft.oopp.g72.WebSocketMadness;
import nl.tudelft.oopp.g72.entities.Question;
import org.springframework.messaging.simp.stomp.StompSession;

public class LocalVariables {
    public static String token;

    public static Long roomId;
<<<<<<< HEAD
    public static String studentCode;
    public static String moderatorCode;

=======
    public static String lectureName;
    public static boolean open;
    public static long scheduledTime;
    public static String joinStudent;
    public static String joinModerator;
>>>>>>> e0801494095140c2fe4c2bb87fd043b33da930df

    public static ObservableList<Question> questions = FXCollections.observableArrayList();
    public static FilteredList<Question> filteredQuestions = new FilteredList<>(questions);
    public static SortedList<Question> sortedQuestions = new SortedList<>(filteredQuestions,
<<<<<<< HEAD
            Comparator.comparingLong(Question::getTime));

=======
            Comparator.comparingLong(Question::getTimestamp));

    public static WebSocketMadness webSocketMadness;
    public static StompSession stompSession;
>>>>>>> e0801494095140c2fe4c2bb87fd043b33da930df
}
