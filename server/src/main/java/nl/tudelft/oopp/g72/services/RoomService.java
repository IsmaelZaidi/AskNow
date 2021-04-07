package nl.tudelft.oopp.g72.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Random;
import nl.tudelft.oopp.g72.models.Room;
import nl.tudelft.oopp.g72.models.User;
import nl.tudelft.oopp.g72.repositories.RoomRepository;
import nl.tudelft.oopp.g72.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    /**
     * Returns a participant entry code.
     * @result String entry code
     */
    public String getParticipantEntryCode() {
        String digits = "abcdefghijklmnopqrstuvwxyzABCEDFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int base = digits.length();
        long date = System.currentTimeMillis();
        String result = new String();
        Random r = new Random();
        while (date >= base) {
            int k = r.nextInt(60) + 1;
            result = digits.charAt((int) ((date + k) % base)) + result;
            date = date / base;
        }
        return result;
    }

    /**
     * Returns a moderator entry code.
     * @result String entry code
     */
    public String getModeratorEntryCode() {
        String digits = "abcdefghijklmnopqrstuvwxyzABCEDFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int base = digits.length();
        long date = System.currentTimeMillis();
        String result = new String();
        Random r = new Random();
        while (date >= base) {
            int k = r.nextInt(60) + 1;
            if (k % 2 == 0) {
                result = digits.charAt((int) ((date + k) % base)) + result;
            } else {
                result = digits.charAt((int) ((2730 | (date + k)) % base)) + result;
            }
            date = date / base;
        }
        return result;
    }

    /**
     * Joins room as student.
     * @param code String
     * @param token String
     * @return joined Room ID
     * @throws Exception for bad requests
     */
    public long joinRoomStudent(String code, String token) throws Exception {
        User user = userRepository.findByToken(token);
        Room room = roomRepository.findByJoincodeStudent(code);
        if (user == null) {
            throw new Exception("There are no users with that token!");
        }
        if (room == null) {
            throw new Exception("There are no rooms with that code!");
        } else {
            if (!isOpen(room.getId())) {
                return 0;
            }
            user.setRoom(room);
            userRepository.save(user);
            return room.getId();
        }
    }

    /**
     * Joins room as moderator.
     * @param code String
     * @param token String
     * @return joined Room ID
     * @throws Exception for bad requests
     */
    public Object joinRoomModerator(String code, String token) throws Exception {
        User user = userRepository.findByToken(token);
        Room room = roomRepository.findByJoincodeModerator(code);
        if (user == null) {
            throw new Exception("There are no users with that token!");
        }
        if (room == null) {
            throw new Exception("There are no rooms with that code!");
        } else {
            user.setRoom(room);
            user.setModerator(true);
            userRepository.save(user);
            return room;
        }
    }

    /**
     * Creates a room.
     * @param token token
     * @param title title
     * @param scheduledTime scheduledTime
     * @return
     */
    public Room createRoom(String token, String title, long scheduledTime) {
        User user = userRepository.findByToken(token);
        if (user == null) {
            return null;
        }
        Room room = new Room(0, title, true, scheduledTime,
                getParticipantEntryCode(), getModeratorEntryCode());
        if (LocalTime.now().toEpochSecond(LocalDate.now(),
                OffsetDateTime.now().getOffset()) < scheduledTime) {
            room.setOpen(false);
        }
        room = roomRepository.save(room);

        user.setRoom(room);
        user.setModerator(true);
        userRepository.save(user);
        return room;
    }

    /**
     * Checks if the room is open for students, updating the status if needed.
     * @param code student join code
     * @return -1 if the room is closed, 0 if it is open and the open time if it is scheduled
     */
    public long isRoomOpen(String code) {
        Room room = roomRepository.findByJoincodeStudent(code);
        if (room.getScheduledTime() <= LocalTime.now().toEpochSecond(LocalDate.now(),
                OffsetDateTime.now().getOffset()) && room.getScheduledTime() != 0) {
            room.setOpen(true);
            room = roomRepository.save(room);
        }
        if (!room.isOpen()) {
            if (room.getScheduledTime() == 0) {
                return -1;
            } else {
                return room.getScheduledTime();
            }
        }
        return 0;
    }

    /**
     * Checks if room is open, updating if needed.
     * @param id the room's id
     * @return true if the room is open, false otherwise
     */
    public boolean isOpen(long id) {
        Room room = roomRepository.getOne(id);
        if (room.getScheduledTime() <= LocalTime.now().toEpochSecond(LocalDate.now(),
                OffsetDateTime.now().getOffset()) && room.getScheduledTime() != 0) {
            room.setOpen(true);
            room = roomRepository.save(room);
        }
        return room.isOpen();
    }

    public void closeRoom(String code) {
        roomRepository.setRoomClosed(code);
    }
}
