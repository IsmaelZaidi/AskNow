package nl.tudelft.oopp.g72;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        MvcResult result = mockMvc.perform(post("/api/v1/ask")
                    .header("Token", "GoodToken")
                    .header("RoomId", 1)
                    .content("What is the meaning of life?"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        Question parsedQuestion = mapper.readValue(json, Question.class);

        assertEquals(question, parsedQuestion);
    }
}
