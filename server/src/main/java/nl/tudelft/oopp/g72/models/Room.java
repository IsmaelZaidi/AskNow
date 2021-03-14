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
            nullable = false,
            unique = true
    )
    private long id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "open",
            nullable = false
    )
    private boolean open;

    @Column(
            name = "scheduled_time",
            nullable = false
    )
    private String scheduledTime;

    @Column(
            name = "joincode_student",
            nullable = false
    )
    private String joincodeStudent;

    @Column(
            name = "joincode_moderator",
            nullable = false
    )
    private String joincodeModerator;

    public Room() {

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

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
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

    public String getJoincodeModerator() {
        return joincodeModerator;
    }

    public void setJoincodeModerator(String joincodeModerator) {
        this.joincodeModerator = joincodeModerator;
    }

    @Override
    public String toString() {
        return "Room{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", open=" + open
                + ", scheduledTime='" + scheduledTime + '\''
                + ", joincodeStudent='" + joincodeStudent + '\''
                + ", joincodeModerator='" + joincodeModerator + '\''
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

        if (id != room.id) {
            return false;
        }
        if (open != room.open) {
            return false;
        }
        if (!Objects.equals(name, room.name)) {
            return false;
        }
        if (!Objects.equals(scheduledTime, room.scheduledTime)) {
            return false;
        }
        if (!Objects.equals(joincodeStudent, room.joincodeStudent)) {
            return false;
        }
        return Objects.equals(joincodeModerator, room.joincodeModerator);
    }
}

