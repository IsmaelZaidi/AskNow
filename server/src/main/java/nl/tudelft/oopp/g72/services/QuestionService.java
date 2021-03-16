package nl.tudelft.oopp.g72.services;

import java.util.Optional;
import nl.tudelft.oopp.g72.models.Question;
import nl.tudelft.oopp.g72.models.Room;
import nl.tudelft.oopp.g72.models.User;
import nl.tudelft.oopp.g72.repositories.QuestionRepository;
import nl.tudelft.oopp.g72.repositories.RoomRepository;
import nl.tudelft.oopp.g72.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    /**
     * Constructor for QuestionService.
     * @param questionRepository Autowired question repository
     * @param userRepository Autowired user repository
     * @param roomRepository Autowired room repository.
     */
    @Autowired
    public QuestionService(@Qualifier("QuestionRepository") QuestionRepository questionRepository,
                           @Qualifier("UserRepository") UserRepository userRepository,
                           @Qualifier("RoomRepository") RoomRepository roomRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    /**
     * Method for adding a Question to the database.
     * @param userToken String token of the one who asked the question
     * @param roomId integer id of the room
     * @param message String containing the question
     * @return a Question entity that corresponds to the one inserted in the database
     */
    public Question addQuestion(String userToken, long roomId, String message) {
        Question question = new Question();

        User user = userRepository.findByToken(userToken);
        if (user == null) {
            return null;
        }

        Optional<Room> roomOptional = roomRepository.findById(roomId);
        Room room;
        if (roomOptional.isPresent()) {
            room = roomOptional.get();
        } else {
            return null;
        }

        question.setUser(user);
        question.setRoom(room);
        question.setText(message);
        question.setTimestamp(System.currentTimeMillis());
        question.setUpvotes(1);
        question.setAnswer(null);
        question.setAnswered(false);

        question = questionRepository.save(question);

        return question;
    }
}
