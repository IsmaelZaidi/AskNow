package nl.tudelft.oopp.g72;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nl.tudelft.oopp.g72.models.User;
import nl.tudelft.oopp.g72.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveAndRetrieveUser() {
        String userNick = "Christoph";
        String userToken = "securedb";
        User user = new User();
        user.setNick(userNick);
        user.setToken(userToken);
        userRepository.save(user);

        User user2 = userRepository.getOne((long) 1);
        assertEquals(user, user2);
    }
}

