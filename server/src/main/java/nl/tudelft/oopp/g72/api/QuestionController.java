package nl.tudelft.oopp.g72.api;

import nl.tudelft.oopp.g72.models.MessageAnswer;
import nl.tudelft.oopp.g72.models.MessageDelete;
import nl.tudelft.oopp.g72.models.MessageUpvote;
import nl.tudelft.oopp.g72.models.Question;
import nl.tudelft.oopp.g72.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    private SimpMessagingTemplate webSocket;

    @PostMapping("/ask")
    void ask(@RequestHeader("Token") String token,
                 @RequestHeader("RoomId") long roomId,
                 @RequestBody String message) {
        Question question = questionService.addQuestion(token, roomId, message);
        if (question == null) {
            throw new IllegalArgumentException("Token or Room ID is wrong");
        }
        webSocket.convertAndSend("/room" + roomId, question);
    }

    @GetMapping(value = "upvote/{questionID}/{userToken}/{roomId}")
    public void upvoteQuestion(@PathVariable long questionID, @PathVariable String userToken,
        Long roomId)
        throws Exception {
        Question question = questionService.upvoteQuestion(questionID,userToken);
        MessageUpvote up = new MessageUpvote(questionID,question.getUpvotes());
        webSocket.convertAndSend("/room" + roomId, up);
    }

    @GetMapping(value = "answer/{questionID}/{userToken}/{roomId}")
    public void answerQuestion(@PathVariable long questionID, @PathVariable String userToken,
        Long roomId)
        throws Exception {
        Question question = questionService.setAsAnswered(questionID, userToken);
        MessageAnswer ans = new MessageAnswer(questionID,true,null);
        webSocket.convertAndSend("/room" + roomId, ans);
    }

    @PostMapping("/answer/{questionID}/{userToken}/{roomId}")
    void answer(@PathVariable long questionID, @PathVariable String userToken,
                Long roomId, @RequestBody String message) throws Exception {
        questionService.answerQuestion(userToken, questionID, message);
        MessageAnswer ans = new MessageAnswer(questionID,true,message);
        webSocket.convertAndSend("/room" + roomId, ans);
    }

    @DeleteMapping("/question/{id}/{roomId}")
    void delete(@RequestHeader("Token") String token, @PathVariable long id,
        Long roomId) {
        boolean success = questionService.deleteQuestion(token, id);
        if (!success) {
            throw new IllegalArgumentException("Bad token or question doesn't exist");
        }

        MessageDelete del = new MessageDelete(id);
        webSocket.convertAndSend("/room" + roomId, del);
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
}
