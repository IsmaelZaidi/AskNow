package nl.tudelft.oopp.g72;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testLogin() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/v1/login")
                    .content("Cristoph"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    void testInfo() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/v1/login")
                    .content("Cristof"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String token = result.getResponse().getContentAsString();
        result = mockMvc.perform(get("/api/v1/info")
                    .header("Token", token))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String name = result.getResponse().getContentAsString();
        assertEquals(name, "Cristof");
    }
}
