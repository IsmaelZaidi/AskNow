package nl.tudelft.oopp.g72.localvariables;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import nl.tudelft.oopp.g72.entities.Question;

public class LocalVariables {
    public static String token;
    public static Long roomId;

    public static ObservableList<Question> questions = FXCollections.observableArrayList();
    public static FilteredList<Question> filteredQuestions = new FilteredList<>(questions);
    public static SortedList<Question> sortedQuestions = new SortedList<>(filteredQuestions);
}
