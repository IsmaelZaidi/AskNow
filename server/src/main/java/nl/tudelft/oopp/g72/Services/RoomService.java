package nl.tudelft.oopp.g72.Services;

import org.springframework.stereotype.Service;


@Service
public class RoomService {

    public String getParticipantEntryCode() {
         String digits = "abcdefghijklmnopqrstuvwxyzABCEDFGHIJKLMNOPQRSTUVWXYZ0123456789";
         int base = digits.length();

         long date = System.currentTimeMillis();
         String result = new String();
         while (date >= base) {
            result = digits.indexOf((int) (date % base)) + result;
            date = date / base;
         }
         result = digits.indexOf((int) date) + result;
         return result;
    }
}
