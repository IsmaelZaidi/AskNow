package nl.tudelft.oopp.g72.api;

import java.util.List;
import nl.tudelft.oopp.g72.models.Question;
import nl.tudelft.oopp.g72.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "upvote/{questionID}/{userToken}")
    public void upvoteQuestion(@PathVariable long questionID, @PathVariable String userToken)
        throws Exception {
        questionService.upvoteQuestion(questionID,userToken);
    }

    @GetMapping(value = "answer/{questionID}/{userToken}")
    public void answerQuestion(@PathVariable long questionID, @PathVariable String userToken)
        throws Exception {
        questionService.setAsAnswered(questionID, userToken);
    }

    @PostMapping("/answer/{questionID}/{userToken}")
    void answer(@PathVariable long questionID, @PathVariable String userToken,
                 @RequestBody String message) throws Exception {
        questionService.answerQuestion(userToken, questionID, message);
    }

    @DeleteMapping("/question/{id}")
    void delete(@RequestHeader("Token") String token, @PathVariable long id) {
        boolean success = questionService.deleteQuestion(token, id);
        if (!success) {
            throw new IllegalArgumentException("Bad token or question doesn't exist");
        }
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
