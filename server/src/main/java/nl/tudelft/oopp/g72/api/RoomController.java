package nl.tudelft.oopp.g72.api;

import nl.tudelft.oopp.g72.models.Room;
import nl.tudelft.oopp.g72.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(value = "api/v1/room/student")
    public String getParticipantEntryCode() {
        return roomService.getParticipantEntryCode();
    }

    @GetMapping(value = "api/v1/room/moderator")
    public String getModeratorEntryCode() {
        return roomService.getModeratorEntryCode();
    }

    @GetMapping(value = "api/v1/join/{code}/{token}")
    public Room joinRoom(@PathVariable String code, String token) throws Exception {
        return roomService.joinRoom(code, token);
    }
}
