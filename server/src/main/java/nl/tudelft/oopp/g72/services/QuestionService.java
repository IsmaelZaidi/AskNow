package nl.tudelft.oopp.g72.services;

import nl.tudelft.oopp.g72.models.Question;
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

    @Autowired
    public QuestionService(@Qualifier("QuestionRepository") QuestionRepository questionRepository,
                           @Qualifier("UserRepository") UserRepository userRepository,
                           @Qualifier("RoomRepository") RoomRepository roomRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    public Question addQuestion(String userToken, long roomId, String message) {
        Question question = new Question();
        question.setUser(userRepository.findByToken(userToken));
        question.setRoom(roomRepository.getOne(roomId));
        question.setTimestamp(System.currentTimeMillis());
        question.setUpvotes(1);
        question.setAnswer(null);
        question.setAnswered(false);

        question = questionRepository.save(question);

        return question;
    }
}
