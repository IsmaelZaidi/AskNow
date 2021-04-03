package nl.tudelft.oopp.g72;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import nl.tudelft.oopp.g72.models.Question;
import nl.tudelft.oopp.g72.services.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    @Test
    void testAsk() throws Exception {
        Question question = new Question();

        when(questionService.addQuestion("GoodToken", 1, "What is the meaning of life?"))
                .thenReturn(question);

        mockMvc.perform(post("/api/v1/ask")
                    .header("Token", "GoodToken")
                    .header("RoomId", 1)
                    .content("What is the meaning of life?"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    void testDelete() throws Exception {
        when(questionService.deleteQuestion("GoodToken", 1)).thenReturn(true);

        /*mockMvc.perform(delete("/api/v1/question/1")
                    .header("Token", "GoodToken"))
                .andExpect(status().is2xxSuccessful());*/
    }

    @Test
    void testRetrieve() throws Exception {
        List<Question> questions = new ArrayList<>();
        Question question = new Question();
        questions.add(question);

        when(questionService.retrieveQuestions("GoodToken", 1))
                .thenReturn(questions);

        MvcResult result = mockMvc.perform(get("/api/v1/retrieve")
                    .header("Token", "GoodToken")
                    .header("Time", 1))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        List<Question> parsedQuestions
                = mapper.readValue(json, new TypeReference<List<Question>>(){});

        assertEquals(questions, parsedQuestions);
        System.out.println(questions);
        System.out.println(parsedQuestions);
    }
}
