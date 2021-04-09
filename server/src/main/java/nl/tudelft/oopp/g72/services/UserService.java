package nl.tudelft.oopp.g72.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import nl.tudelft.oopp.g72.models.User;
import nl.tudelft.oopp.g72.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Class handling UserService.
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    /**
     * UserService constructor.
     *
     * @param userRepository userRepository
     */
    @Autowired
    public UserService(@Qualifier("UserRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Adds user and returns token.
     *
     * @param nick the nickname of the user
     * @return the token associated with the user
     */
    public String add(String nick, boolean moderator) {
        User user = new User();
        user.setNick(nick);
        user.setToken(UUID.randomUUID().toString());
        user.setModerator(moderator);

        user = userRepository.save(user);
        return user.getToken();
    }

    /**
     * Finds and returns the user with the specified token.
     *
     * @param token the user's token
     * @return a User entity with the specified token
     */
    public User findByToken(String token) {
        return userRepository.findByToken(token);
    }

    /**
     * Lists the users in a room.
     *
     * @param roomId the room's id
     * @return a List of Users that are in the room
     */
    public List<User> usersInRoom(long roomId) {
        return userRepository.usersInRoom(roomId);
    }

    /**
     * Removes a user from a joined room.
     *
     * @param token user's token
     * @throws Exception e
     */
    public void leaveRoom(String token) throws Exception {
        User user = userRepository.findByToken(token);
        if (user == null) {
            throw new Exception("Bad token");
        }
        user.setRoom(null);
        userRepository.save(user);
    }

    /**
     * Getter to get user id from user token.
     *
     * @param token user token
     * @return user id
     * @throws Exception if token does not match a user
     */
    public long getId(String token) throws Exception {
        User user = userRepository.findByToken(token);
        if (user == null) {
            throw new Exception("Bad token");
        }
        return user.getId();
    }
}
