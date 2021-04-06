package nl.tudelft.oopp.g72.api;

import java.util.List;
import nl.tudelft.oopp.g72.models.User;
import nl.tudelft.oopp.g72.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    String login(@RequestBody String nick) {
        String token = userService.add(nick, false);
        if (token == null) {
            throw new IllegalArgumentException("Nickname already taken");
        }
        return token;
    }

    @GetMapping("/getId")
    long getId(@RequestHeader("Token") String token) throws Exception {
        return userService.getId(token);
    }

    @GetMapping("/participants")
    List<User> getParticipants(@RequestHeader("Token") String token,
                               @RequestHeader("RoomId") long roomId) {
        User user = userService.findByToken(token);
        if (user == null) {
            throw new IllegalArgumentException("Bad token");
        }
        return userService.usersInRoom(roomId);
    }

    @GetMapping("/participants/leave")
    void leaveRoom(@RequestHeader("Token") String token) {
        try {
            userService.leaveRoom(token);
        } catch (Exception e) {
            throw new IllegalArgumentException("Bad token");
        }
    }

}
