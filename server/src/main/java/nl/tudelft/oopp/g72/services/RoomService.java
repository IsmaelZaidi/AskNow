package nl.tudelft.oopp.g72.services;

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
        room = roomRepository.save(room);

        user.setRoom(room);
        user.setModerator(true);
        userRepository.save(user);
        return room;
    }

    public boolean isRoomOpen(String code) {
        boolean value = roomRepository.isOpen(code);
        return value;
    }

    public boolean isOpen(long id) {
        boolean value = roomRepository.isOpenLong(id);
        return value;
    }

    public void closeRoom(String code) {
        roomRepository.setRoomClosed(code);
    }
}
