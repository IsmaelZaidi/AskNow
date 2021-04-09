package nl.tudelft.oopp.g72.api;

import java.util.List;
import nl.tudelft.oopp.g72.models.MessageAnswer;
import nl.tudelft.oopp.g72.models.MessageDelete;
import nl.tudelft.oopp.g72.models.MessageUpvote;
import nl.tudelft.oopp.g72.models.Question;
import nl.tudelft.oopp.g72.services.QuestionService;
import nl.tudelft.oopp.g72.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class handling questions on the server side.
 */
@RestController
@RequestMapping("api/v1")
public class QuestionController {
    private final QuestionService questionService;
    private RoomService roomService;

    /**
     * QuestionController constructor.
     *
     * @param questionService questionService
     * @param roomService roomService
     */
    @Autowired
    public QuestionController(QuestionService questionService, RoomService roomService) {
        this.questionService = questionService;
        this.roomService = roomService;
    }

    @Autowired
    private SimpMessagingTemplate webSocket;

    /**
     * Method for asking questions. Checks for status of the room, if
     * closed or token or room ID is wrong, user is notified.
     *
     * @param token token of the user
     * @param roomId id of room
     * @param message message content
     */
    @PostMapping("/ask")
    void ask(@RequestHeader("Token") String token,
                 @RequestHeader("RoomId") long roomId,
                 @RequestBody String message) {
        boolean value = roomService.isOpen(roomId);
        if (value) {
            Question question = questionService.addQuestion(token, roomId, message);
            if (question == null) {
                throw new IllegalArgumentException("Token or Room ID is wrong");
            }
            webSocket.convertAndSend("/room" + roomId + "question", question);
        } else {
            throw new IllegalArgumentException("Room is closed");
        }
    }

    /**
     * Method for upvoting question.
     *
     * @param questionID id of question
     * @param userToken token of user
     * @param roomId id of room
     * @throws Exception if method fails
     */
    @GetMapping(value = "/upvote/{questionID}/{userToken}/{roomId}")
    public void upvoteQuestion(@PathVariable long questionID, @PathVariable String userToken,
        @PathVariable long roomId)
        throws Exception {
        boolean value = roomService.isOpen(roomId);
        if (value) {
            Question question = questionService.upvoteQuestion(questionID, userToken);
            MessageUpvote up = new MessageUpvote(questionID, question.getUpvotes());
            webSocket.convertAndSend("/room" + roomId + "upvote", up);
        } else {
            throw new IllegalArgumentException("Room is closed");
        }
    }

    /**
     * Method for answering a question.
     *
     * @param questionID id of question
     * @param userToken token of user
     * @param roomId id of room
     * @throws Exception if the method fails
     */
    @GetMapping(value = "/answer/{questionID}/{userToken}/{roomId}")
    public void answerQuestion(@PathVariable long questionID, @PathVariable String userToken,
        @PathVariable long roomId)
        throws Exception {
        questionService.setAsAnswered(questionID, userToken);
        MessageAnswer ans = new MessageAnswer(questionID,true,null);
        webSocket.convertAndSend("/room" + roomId + "answer", ans);
    }

    @PostMapping("/answer/{questionID}/{userToken}/{roomId}")
    void answer(@PathVariable long questionID, @PathVariable String userToken,
                @PathVariable long roomId, @RequestBody String message) throws Exception {
        questionService.answerQuestion(userToken, questionID, message);
        MessageAnswer ans = new MessageAnswer(questionID,true,message);
        webSocket.convertAndSend("/room" + roomId + "answer", ans);
    }

    /**
     * Method to delete questions.
     *
     * @param token token of user
     * @param id id of question
     * @param roomId id of room
     */
    @DeleteMapping("/question/{id}/{roomId}")
    void delete(@RequestHeader("Token") String token, @PathVariable long id,
        @PathVariable long roomId) {
        boolean success = questionService.deleteQuestion(token, id);
        if (!success) {
            throw new IllegalArgumentException("Bad token or question doesn't exist");
        }

        MessageDelete del = new MessageDelete(id);
        webSocket.convertAndSend("/room" + roomId + "delete", del);
    }

    @GetMapping("/retrieve")
    List<Question> retrieve(@RequestHeader("Token") String token,
                            @RequestHeader("Time") long time) {
        List<Question> questions = questionService.retrieveQuestions(token, time);
        if (questions == null) {
            throw new IllegalArgumentException("Bad token");
        }
        return questions;
    }

    /**
     * Method for editing questions.
     *
     * @param token token of user
     * @param id id of question
     * @param newText text to replace original text by
     * @throws Exception if method fails
     */
    @PostMapping("/edit/{token}/{id}")
    void edit(@PathVariable String token, @PathVariable long id,
              @RequestBody String newText) throws Exception {
        Question question = questionService.editQuestion(token, id, newText);
        webSocket.convertAndSend("/room" + question.getRoom().getId() + "edit", question);
    }
}
