package nl.tudelft.oopp.g72.Services;

import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class RoomService {

    public String getParticipantEntryCode() {
         String digits = "abcdefghijklmnopqrstuvwxyzABCEDFGHIJKLMNOPQRSTUVWXYZ0123456789";
         int base = digits.length();

         long date = System.currentTimeMillis();
         String result = new String();
         Random r = new Random();
         while (date >= base) {
             int k = r.nextInt(60)+1;
             result = digits.charAt((int) ((date+k) % base)) + result;
             date = date / base;
         }

         return result;
    }

    public String getModeratorEntryCode() {
        String digits = "abcdefghijklmnopqrstuvwxyzABCEDFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int base = digits.length();

        long date = System.currentTimeMillis();
        String result = new String();
        Random r = new Random();
        while (date >= base) {
            int k = r.nextInt(60)+1;
            if(k%2 == 0)
                result = digits.charAt((int) ((date+k) % base)%2) + result;
            else
                result = digits.charAt((int) (~(date+k) % base)) + result;
            date = date / base;
        }
        
        return result;
    }
}
