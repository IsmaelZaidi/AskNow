package nl.tudelft.oopp.g72.repositories;

import java.util.Collection;
import java.util.List;
import nl.tudelft.oopp.g72.models.Question;
import nl.tudelft.oopp.g72.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("QuestionRepository")
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT q FROM Question q WHERE q.id = ?1")
    Question findById(String id);

    @Query("SELECT q FROM Question q WHERE q.timestamp > ?1 AND q.room = ?2")
    List<Question> findQuestionsAfter(long time, Room room);
}
