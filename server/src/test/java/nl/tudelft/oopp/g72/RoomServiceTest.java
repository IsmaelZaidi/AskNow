package nl.tudelft.oopp.g72;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import nl.tudelft.oopp.g72.models.Room;
import nl.tudelft.oopp.g72.models.User;
import nl.tudelft.oopp.g72.repositories.RoomRepository;
import nl.tudelft.oopp.g72.repositories.UserRepository;
import nl.tudelft.oopp.g72.services.RoomService;
import nl.tudelft.oopp.g72.services.UserService;

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
    public void joinRoomStudentTest() throws Exception {
        User user = new User();
        user.setToken("test");
        when(userRepository.findByToken("test")).thenReturn(user);
        Room room = new Room(1, "room", true, 1, "123456", "123456");
        when(roomRepository.findByJoincodeStudent("123456")).thenReturn(room);
        when(roomRepository.getOne(1L)).thenReturn(room);
        when(roomRepository.save(room)).thenReturn(room);
        long value = roomService.joinRoomStudent("123456", "test");
        Assertions.assertEquals(1, value);
    }


    @Test
    public void isOpenTest() {
        Room room = new Room(1, "room", true, 1, "123456", "123456");
        when(roomRepository.getOne(1L)).thenReturn(room);
        when(roomRepository.save(room)).thenReturn(room);
        boolean value = roomService.isOpen(1);
        Assertions.assertEquals(true, value);
    }

    @Test
    public void isRoomOpenTest() {
        Room room = new Room(1, "room", true, 1, "123456", "123456");
        when(roomRepository.findByJoincodeStudent("123456")).thenReturn(room);
        when(roomRepository.save(room)).thenReturn(room);
        long value = roomService.isRoomOpen("123456");
        Assertions.assertEquals(value, 0);
    }

    @Test
    public void closeRoomTest() {
        Room room = new Room(1, "room", false, 0, "123456", "123456");
        when(roomRepository.save(room)).thenReturn(room);
        when(roomRepository.findByJoincodeStudent("123456")).thenReturn(room);
        Assertions.assertEquals(false, room.isOpen());
    }


}
