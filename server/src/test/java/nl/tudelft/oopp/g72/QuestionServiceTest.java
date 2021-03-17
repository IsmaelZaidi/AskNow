package nl.tudelft.oopp.g72;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @Test
    void testDeleteQuestion() {
        User user = new User();
        Room room = new Room();
        user.setRoom(room);
        when(userRepository.findByToken("GoodToken")).thenReturn(user);

        Question question = new Question();
        question.setRoom(room);
        question.setUser(user);
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));

        assertTrue(questionService.deleteQuestion("GoodToken", 1));
        assertFalse(questionService.deleteQuestion("BadToken", 1));
        assertFalse(questionService.deleteQuestion("GoodToken", 2));
    }
}
