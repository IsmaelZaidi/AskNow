package nl.tudelft.oopp.g72;

import nl.tudelft.oopp.g72.models.Room;
import nl.tudelft.oopp.g72.models.User;
import nl.tudelft.oopp.g72.repositories.RoomRepository;
import nl.tudelft.oopp.g72.repositories.UserRepository;
import nl.tudelft.oopp.g72.services.RoomService;
import nl.tudelft.oopp.g72.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.time.OffsetDateTime;

@SpringBootTest
public class RoomServiceTest {
    @Autowired
    private RoomService roomService;

    @MockBean
    private RoomRepository roomRepository;

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;



    @Test
    public void joinRoomStudentTest(){
        User user = new User();
        user.setToken("foo");
        Room room = new Room(1, "room", true, 1, "123456", "123456");
        user.setRoom(room);
        userRepository.save(user);
        Assertions.assertEquals(1, room.getId());
    }

    @Test
    public void joinRoomModeratorTest(){
        User user = new User();
        user.setToken("foo");
        Room room = new Room(1, "room", true, 1, "123456", "123456");
        user.setRoom(room);
        userRepository.save(user);
        Assertions.assertEquals(1, room.getId());
    }

    @Test
    public void isRoomOpenTest() {
        Room room = new Room(1, "room", true, 1, "123456", "123456");
        Assertions.assertEquals(true, room.isOpen());
    }


}
