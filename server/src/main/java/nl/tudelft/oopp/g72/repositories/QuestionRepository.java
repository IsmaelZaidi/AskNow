package nl.tudelft.oopp.g72.repositories;

import nl.tudelft.oopp.g72.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("QuestionRepository")
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
