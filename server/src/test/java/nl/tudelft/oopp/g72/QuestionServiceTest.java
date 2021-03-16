package nl.tudelft.oopp.g72;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import nl.tudelft.oopp.g72.models.Question;
import nl.tudelft.oopp.g72.models.Room;
import nl.tudelft.oopp.g72.models.User;
import nl.tudelft.oopp.g72.repositories.QuestionRepository;
import nl.tudelft.oopp.g72.repositories.RoomRepository;
import nl.tudelft.oopp.g72.repositories.UserRepository;
import nl.tudelft.oopp.g72.services.QuestionService;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class QuestionServiceTest {
    @Autowired
    private QuestionService questionService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoomRepository roomRepository;

    @MockBean
    private QuestionRepository questionRepository;

    @Test
    void testAddQuestion() {
        User user = new User();
        when(userRepository.findByToken("GoodToken")).thenReturn(user);

        Room room = new Room();
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));

        when(questionRepository.save(any())).thenAnswer(AdditionalAnswers.returnsFirstArg());

        Question returnedQuestion = questionService.addQuestion("GoodToken", 1L, "asdasdasd");

        assertEquals(user, returnedQuestion.getUser());
        assertEquals(room, returnedQuestion.getRoom());
        assertEquals("asdasdasd", returnedQuestion.getText());
    }
}
