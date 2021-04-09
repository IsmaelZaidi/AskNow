package nl.tudelft.oopp.g72;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.tudelft.oopp.g72.models.Room;
import nl.tudelft.oopp.g72.services.RoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class RoomControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    @Test
    void testJoinRoom() throws Exception {
        when(roomService.joinRoomStudent("123XYZ", "test"))
                .thenReturn(1L);
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/join")
                .header("Token", "test")
                .header("Code", "123XYZ"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        assertEquals("1", mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testCreateRoom() throws Exception {
        Room room = new Room();
        when(roomService.createRoom("test", "Title", 1L)).thenReturn(room);
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/create")
                .header("Token", "test")
                .header("ScheduledTime", 1L)
                .content("Title"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        Room retRoom = mapper.readValue(json, Room.class);
        assertEquals(room, retRoom);
    }

    @Test
    void testRoomOpen() throws Exception {
        when(roomService.isRoomOpen("test")).thenReturn(1L);
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/open")
                .header("Code", "test"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        assertEquals("1", mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testCloseRoom() throws Exception {
        mockMvc.perform(get("/api/v1/close")
                .header("Code", "test"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    void testInfo() throws Exception {
        when(roomService.info(1L)).thenReturn("test");
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/info")
                .header("RoomId", "1"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        assertEquals("test", mvcResult.getResponse().getContentAsString());
    }
}
