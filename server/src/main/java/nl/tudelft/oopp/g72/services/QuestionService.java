package nl.tudelft.oopp.g72.services;

import java.util.List;
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

/**
 * Class handling QuestionService.
 */
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    /**
     * Constructor for QuestionService.
     *
     * @param questionRepository autowired question repository
     * @param userRepository autowired user repository
     * @param roomRepository autowired room repository
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
     *
     * @param userToken string token of the one who asked the question
     * @param roomId integer id of the room
     * @param message string containing the question
     * @return a question entity that corresponds to the one inserted in the database
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

    /**
     * Upvoting a question.
     *
     * @param questionID question id
     * @param token user token
     * @throws Exception throws exception
     */
    public Question upvoteQuestion(long questionID, String token) throws Exception {
        User user = userRepository.findByToken(token);
        if (user == null) {
            throw new Exception("There are no users with that token!");
        }

        Optional<Question> optionalQuestion = questionRepository.findById(questionID);
        if (optionalQuestion.isEmpty()) {
            throw new Exception("There are no questions with that ID!");
        }

        Question question = optionalQuestion.get();

        if (question.getRoom().getId() != user.getRoom().getId()) {
            throw new Exception("You are not in the same room as the question!");
        }

        question.setUpvotes(question.getUpvotes() + 1);
        return questionRepository.save(question);
    }

    /**
     * It sets boolean value.
     *
     * @param questionID question id
     * @param token user token
     * @return question object returned
     * @throws Exception if method fails
     */
    public Question setAsAnswered(long questionID, String token) throws Exception {
        User user = userRepository.findByToken(token);
        if (user == null) {
            throw new Exception("There are no users with that token!");
        }
        if (!user.getModerator()) {
            throw new Exception("You are not a moderator!");
        }

        Optional<Question> optionalQuestion = questionRepository.findById(questionID);
        if (optionalQuestion.isEmpty()) {
            throw new Exception("There are no questions with that ID!");
        }

        Question question = optionalQuestion.get();
        question.setAnswered(true);
        return questionRepository.save(question);
    }

    /**
     * Answer question.
     *
     * @param token user token
     * @param questionID question id
     * @param message message
     * @return returns question object.
     * @throws Exception if method fails
     */
    public Question answerQuestion(String token, long questionID, String message)
        throws Exception {
        User user = userRepository.findByToken(token);
        if (user == null) {
            throw new Exception("There are no users with that token!");
        }
        if (!user.getModerator()) {
            throw new Exception("You are not a moderator!");
        }

        Optional<Question> optionalQuestion = questionRepository.findById(questionID);
        if (optionalQuestion.isEmpty()) {
            throw new Exception("There are no questions with that ID!");
        }

        Question question = optionalQuestion.get();
        question.setAnswered(true);
        question.setText(message);
        return questionRepository.save(question);
    }

    /**
     * Deletes a question asked by the user.
     *
     * @param token user token
     * @param questionId question id
     * @return true if the deletion succeeded, false otherwise
     */
    public boolean deleteQuestion(String token, long questionId) {
        User user = userRepository.findByToken(token);
        if (user == null) {
            return false;
        }

        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        if (optionalQuestion.isEmpty()) {
            return false;
        }
        Question question = optionalQuestion.get();

        Room room = user.getRoom();
        if (!question.getRoom().equals(room) || (!question.getUser().equals(user)
                && !user.getModerator())) {
            return false;
        }

        questionRepository.delete(question);
        return true;
    }

    /**
     * Retrieves a List of Questions asked in a user's room after a certain time.
     *
     * @param userToken user token
     * @param time time of last question
     * @return list of questions sent after that time
     */
    public List<Question> retrieveQuestions(String userToken, long time) {
        User user = userRepository.findByToken(userToken);
        if (user == null) {
            return null;
        }

        Room room = user.getRoom();
        return questionRepository.findQuestionsAfter(time, room);
    }

    /**
     * Edits question asked earlier.
     *
     * @param token user token
     * @param id question id
     * @param messsage new question content
     * @return the new question
     * @throws Exception if method fails
     */
    public Question editQuestion(String token, long id, String messsage) throws Exception {
        User user = userRepository.findByToken(token);
        if (user == null) {
            throw new Exception("Bad token");
        }

        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isEmpty()) {
            throw new Exception("Question does not exist");
        }
        Question question = optionalQuestion.get();

        Room room = user.getRoom();
        if (!question.getRoom().equals(room) || !user.getModerator()) {
            throw new Exception("You don't have the permissions to edit this question");
        }

        question.setText(messsage);
        return questionRepository.save(question);
    }
}
