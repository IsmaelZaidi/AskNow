package nl.tudelft.oopp.g72.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity(name = "Room")
@Table(name = "Room")
public class Room {
    @Id
    @SequenceGenerator(
            name = "room_id_sequence",
            sequenceName = "room_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "room_id_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private long id;

    @Column(
            name = "name",
            updatable = false
    )
    private String name;

    @Column(
            name = "opened",
            updatable = false
    )
    private boolean opened;

    @Column(
            name = "scheduled_time",
            updatable = false
    )
    private String scheduledTime;

    @Column(
            name = "joincode_student",
            updatable = false
    )
    private String joincodeStudent;

    @Column(
            name = "joincode_mod",
            updatable = false
    )
    private String joincodeMod;

    public Room() {

    }

    /**
     * Creates a room object.
     * @param name indicates the name
     * @param joincodeStudent indicates the join code for the student
     * @param joincodeMod indicates the join code for the moderator
     */
    public Room(String name, String joincodeStudent, String joincodeMod) {
        this.name = name;
        this.joincodeStudent = joincodeStudent;
        this.joincodeMod = joincodeMod;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public String getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(String scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public String getJoincodeStudent() {
        return joincodeStudent;
    }

    public void setJoincodeStudent(String joincodeStudent) {
        this.joincodeStudent = joincodeStudent;
    }

    public String getJoincodeMod() {
        return joincodeMod;
    }

    public void setJoincodeMod(String joincodeMod) {
        this.joincodeMod = joincodeMod;
    }

    @Override
    public String toString() {
        return "Room{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", opened=" + opened
                + ", scheduled_time='" + scheduledTime + '\''
                + ", joincodeStudent='" + joincodeStudent + '\''
                + ", joincodeMod='" + joincodeMod + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Room)) {
            return false;
        }
        Room room = (Room) o;
        return id == room.id
                && opened == room.opened
                && name.equals(room.name)
                && Objects.equals(scheduledTime, room.scheduledTime)
                && joincodeStudent.equals(room.joincodeStudent)
                && joincodeMod.equals(room.joincodeMod);
    }

}

//look again at forgein key constraints
