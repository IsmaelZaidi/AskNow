package nl.tudelft.oopp.g72.services;



import java.util.UUID;
import java.util.Optional;
import nl.tudelft.oopp.g72.models.User;
import org.springframework.stereotype.Service;
import nl.tudelft.oopp.g72.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;




@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(@Qualifier("UserRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
