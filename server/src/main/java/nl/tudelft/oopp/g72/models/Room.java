package nl.tudelft.oopp.g72.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Class handling Room object.
 */
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
    private long scheduledTime;

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

    /**
     * Room constructor.
     *
     * @param id room id
     * @param name room name
     * @param open if open or not
     * @param scheduledTime scheduled open time
     * @param joincodeStudent student code
     * @param joincodeModerator moderator code
     */
    public Room(long id, String name, boolean open, long scheduledTime,
                String joincodeStudent, String joincodeModerator) {
        this.id = id;
        this.name = name;
        this.open = open;
        this.scheduledTime = scheduledTime;
        this.joincodeStudent = joincodeStudent;
        this.joincodeModerator = joincodeModerator;
    }

    /**
     * Getter to get id of room.
     *
     * @return room id
     */
    public long getId() {
        return id;
    }

    /**
     * Setter to set id of room.
     *
     * @param id new room id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter to get room name.
     *
     * @return room name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter to set/change room name.
     *
     * @param name new room name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Check if room is open.
     *
     * @return true if open, false if not.
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Setter to set room open or not.
     *
     * @param open true if open, false if not.
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

    /**
     * Getter to get scheduled time to open.
     *
     * @return scheduled time
     */
    public long getScheduledTime() {
        return scheduledTime;
    }

    /**
     * Setter to set/change scheduled time to open.
     *
     * @param scheduledTime new schduled time
     */
    public void setScheduledTime(long scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    /**
     * Getter to get student code.
     *
     * @return student code
     */
    public String getJoincodeStudent() {
        return joincodeStudent;
    }

    /**
     * Setter to set student code.
     *
     * @param joincodeStudent new student code.
     */
    public void setJoincodeStudent(String joincodeStudent) {
        this.joincodeStudent = joincodeStudent;
    }

    /**
     * Getter to get moderator code.
     *
     * @return moderator code
     */
    public String getJoincodeModerator() {
        return joincodeModerator;
    }

    /**
     * Setter to set moderator code.
     *
     * @param joincodeModerator new moderator code
     */
    public void setJoincodeModerator(String joincodeModerator) {
        this.joincodeModerator = joincodeModerator;
    }

    /**
     * Make room object into string overview.
     *
     * @return String with overview of room object.
     */
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

    /**
     * Check if room is equal to any object.
     *
     * @param o object to compare to
     * @return true if equal, false if not
     */
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

