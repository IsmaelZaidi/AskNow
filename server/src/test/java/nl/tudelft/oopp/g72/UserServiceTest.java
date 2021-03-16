package nl.tudelft.oopp.g72;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import nl.tudelft.oopp.g72.models.User;
import nl.tudelft.oopp.g72.repositories.UserRepository;
import nl.tudelft.oopp.g72.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testAdd() {
        User user = new User();
        user.setToken("test");

        when(userRepository.save(any())).thenReturn(user);

        String token = userService.add("foo");
        assertEquals("test", token);
    }

    @Test
    void testFindByToken() {
        User user = new User();
        user.setToken("foo");

        when(userRepository.findByToken("foo")).thenReturn(user);

        User user2 = userService.findByToken("foo");
        assertEquals(user, user2);
    }

    @Test
    void testParticipants() {
        User user = new User();

        List<User> p = new ArrayList<>();
        p.add(user);

        when(userRepository.usersInRoom(1)).thenReturn(p);

        List<User> r = userService.usersInRoom(1);

        assertEquals(p, r);
    }
}
