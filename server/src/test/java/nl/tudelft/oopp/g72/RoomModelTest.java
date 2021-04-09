package nl.tudelft.oopp.g72;

import nl.tudelft.oopp.g72.models.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoomModelTest {
    Room room = new Room(1, "room", true, 1, "123456", "678901");


    @Test
    public void constructorTest() {
        Room room = new Room(1, "room", true, 1, "123456", "678901");
        Assertions.assertNotNull(room);
    }

    @Test
    public void getIdTest() {
        Assertions.assertEquals(1, room.getId());
    }

    @Test
    public void setIdTest() {
        room.setId(2);
        Assertions.assertEquals(2, room.getId());
    }

    @Test
    public void getNameTest() {
        Assertions.assertEquals("room", room.getName());
    }

    @Test
    public void setNameIdTest() {
        room.setName("hello");
        Assertions.assertEquals("hello", room.getName());
    }

    @Test
    public void isOpenTest() {
        Assertions.assertEquals(true, room.isOpen());
    }

    @Test
    public void setOpenTest() {
        room.setOpen(false);
        Assertions.assertEquals(false, room.isOpen());
    }

    @Test
    public void getScheduledTimeTest() {
        Assertions.assertEquals(1, room.getScheduledTime());
    }

    @Test
    public void setScheduledTimeTest() {
        room.setScheduledTime(3);
        Assertions.assertEquals(3, room.getScheduledTime());
    }

    @Test
    public void getJoincodeStudent() {
        Assertions.assertEquals("123456", room.getJoincodeStudent());
    }

    @Test
    public void setJoincodeStudent() {
        room.setJoincodeStudent("111111");
        Assertions.assertEquals("111111", room.getJoincodeStudent());
    }

    @Test
    public void getJoincodeModerator() {
        Assertions.assertEquals("678901", room.getJoincodeModerator());
    }

    @Test
    public void setJoincodeModerator() {
        room.setJoincodeModerator("1111");
        Assertions.assertEquals("1111", room.getJoincodeModerator());
    }

    @Test
    public void toStringTest() {
        String result = room.toString();
        Assertions.assertEquals("Room{id=1, name='room', open=true, scheduledTime='1', " +
                "joincodeStudent='123456', joincodeModerator='678901'}", result);
    }

    @Test
    public void equalsSameTest() {
        Room room2 = new Room(1, "room", true, 1, "123456", "678901");
        Assertions.assertEquals(room, room2);
    }

    @Test
    public void equalsDifferentTest() {
        Room room2 = new Room(2, "room", false, 1, "123456", "678901");
        Assertions.assertNotEquals(room, room2);
    }

}
