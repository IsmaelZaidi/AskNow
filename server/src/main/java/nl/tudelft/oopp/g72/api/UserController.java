package nl.tudelft.oopp.g72.api;

import nl.tudelft.oopp.g72.models.User;
import nl.tudelft.oopp.g72.services.RoomService;
import nl.tudelft.oopp.g72.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
        String token = userService.add(nick);
        if (token == null) {
            throw new IllegalArgumentException("Nickname already taken");
        }
        return token;
    }

    @GetMapping("/info")
    String getNick(@RequestHeader("Token") String token) {
        User user = userService.findByToken(token);
        if (user == null) {
            throw new IllegalArgumentException("Bad token");
        }
        return user.getNick();
    }

    @GetMapping("/participants")
    List<User> getParticipants(@RequestHeader("Token") String token, @RequestHeader("RoomId") long roomId) {
        User user = userService.findByToken(token);
        if (user == null) {
            throw new IllegalArgumentException("Bad token");
        }
        return userService.usersInRoom(roomId);
    }
}
