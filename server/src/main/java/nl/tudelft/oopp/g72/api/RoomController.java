package nl.tudelft.oopp.g72.api;

import nl.tudelft.oopp.g72.models.Room;
import nl.tudelft.oopp.g72.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping

public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Endpoint for joining a room.
     * @param token user's token
     * @param code room join code
     * @return a long with the room id if joined as student, or a Room entity if joined as moderator
     * @throws Exception if token or code are invalid
     */
    @GetMapping(value = "api/v1/join")
    public Object joinRoom(@RequestHeader("Token") String token,
                         @RequestHeader("Code") String code) throws Exception {
        try {
            return roomService.joinRoomStudent(code, token);
        } catch (Exception e) {
            return roomService.joinRoomModerator(code, token);
        }
    }

    /**
     * Creates the post method for creating a room.
     * @param token token
     * @param scheduledTime scheduledTime
     * @param title title
     * @return returns a room
     */
    @PostMapping(value = "api/v1/create")
    public Room createRoom(@RequestHeader ("Token") String token,
                           @RequestHeader ("ScheduledTime") long scheduledTime,
                           @RequestBody String title) {
        Room room = roomService.createRoom(token, title, scheduledTime);
        if (room == null) {
            throw new IllegalArgumentException("room is empty");
        }
        return room;
    }

}
