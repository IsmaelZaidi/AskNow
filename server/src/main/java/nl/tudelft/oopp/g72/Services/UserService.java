package nl.tudelft.oopp.g72.services;

import java.util.Optional;
import java.util.UUID;
import nl.tudelft.oopp.g72.models.User;
import nl.tudelft.oopp.g72.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;




@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(@Qualifier("UserRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Adds user and returns token.
     * @param nick when entering nickname should return token
     * @return returning the token
     */
    public String add(String nick) {
        User user = new User(nick, UUID.randomUUID().toString());

        User search = userRepository.findByNick(nick);
        if (search != null) {
            return null;
        }

        userRepository.save(user);
        return user.getToken();
    }

    public User findByToken(String token) {
        return userRepository.findByToken(token);
    }
}
