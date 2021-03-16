package nl.tudelft.oopp.g72.services;

import java.util.Random;

import nl.tudelft.oopp.g72.models.Room;
import nl.tudelft.oopp.g72.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
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

    public Room joinRoom(String code) throws Exception {
      if(roomRepository.findByJoincodeStudent(code) == null)
          throw new Exception("There are no rooms with that code!");
      else
          return roomRepository.findByJoincodeStudent(code);
    }
}
