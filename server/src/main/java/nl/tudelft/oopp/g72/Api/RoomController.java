package nl.tudelft.oopp.g72.Api;

import nl.tudelft.oopp.g72.Services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/room")

public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public String getParticipantEntryCode(){
        return roomService.getParticipantEntryCode();
    }

    /*@GetMapping
    public String getModeratorEntryCode(){
        return roomService.getModeratorEntryCode();
    }*/
}
