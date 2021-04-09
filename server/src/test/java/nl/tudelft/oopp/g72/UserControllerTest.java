package nl.tudelft.oopp.g72;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.tudelft.oopp.g72.models.User;
import nl.tudelft.oopp.g72.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testLogin() throws Exception {
        when(userService.add("Cristoph", false)).thenReturn("test");
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/login")
                .content("Cristoph"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        assertEquals("test", mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testGetId() throws Exception {
        when(userService.getId("test")).thenReturn(1L);
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/getId")
                .header("Token", "test"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        assertEquals("1", mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testGetParticipants() throws Exception {
        User user = new User();
        when(userService.findByToken("test")).thenReturn(user);
        when(userService.usersInRoom(1L)).thenReturn(Arrays.asList(user, user, user));
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/participants")
                .header("Token", "test")
                .header("RoomId", 1L))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(json);
        assertEquals(3, node.size());
    }

    @Test
    void testLeave() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/participants/leave")
                .header("Token", "test"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }
}
