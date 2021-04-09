package nl.tudelft.oopp.g72;

import nl.tudelft.oopp.g72.models.Room;
import nl.tudelft.oopp.g72.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserModelTest {
    User iss = new User(2, "bar", "token", null, true);

    @Test
    public void constructorTest() {
        User user = new User(1, "foo", "token", null, false);
        Assertions.assertNotNull(user);
    }

    @Test
    public void getIdTest() {
        Assertions.assertEquals(2, iss.getId());
    }

    @Test
    public void setIdTest() {
        iss.setId(3);
        Assertions.assertEquals(3, iss.getId());
    }

    @Test
    public void getNickTest() {
        Assertions.assertEquals("bar", iss.getNick());
    }

    @Test
    public void setNickTest() {
        iss.setNick("foo");
        Assertions.assertEquals("foo", iss.getNick());
    }

    @Test
    public void getTokenTest() {
        Assertions.assertEquals("token", iss.getToken());
    }

    @Test
    public void setTokenTest() {
        iss.setToken("newToken");
        Assertions.assertEquals("newToken", iss.getToken());
    }

    @Test
    public void getRoomTest() {
        Assertions.assertEquals(null, iss.getRoom());
    }

    @Test
    public void setRoomTest() {
        Room room = new Room();
        iss.setRoom(room);
        Assertions.assertEquals(room, iss.getRoom());
    }

    @Test
    public void getModeratorTest() {
        Assertions.assertEquals(true, iss.getModerator());
    }

    @Test
    public void setModeratorTest() {
        iss.setModerator(false);
        Assertions.assertEquals(false, iss.getModerator());
    }

    @Test
    public void toStringTest() {
        String result = iss.toString();
        Assertions.assertEquals("User{id=2, nick='bar', token='token', "
                + "room=null, moderator=true}", result);
    }

    @Test
    public void equalsSameTest() {
        User iss2 = new User(2, "bar", "token", null, true);
        Assertions.assertEquals(iss2, iss);
    }

    @Test
    public void equalsDifferentTest() {
        User notIss = new User(23, "bar", "token", null, false);
        Assertions.assertNotEquals(iss, notIss);

    }
}
