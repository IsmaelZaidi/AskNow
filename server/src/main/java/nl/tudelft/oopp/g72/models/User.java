package nl.tudelft.oopp.g72.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Class handling User object.
 */
@Entity(name = "User")
@Table(name = "user")
public class  User {
    @Id
    @SequenceGenerator(
        name = "user_id_sequence",
        sequenceName = "user_id_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_id_sequence"
    )
    @Column(
        name = "id",
        nullable = false,
        unique = true
    )
    private long id;

    @Column(
        name = "nick",
        nullable = false
    )
    private String nick;

    @JsonIgnore
    @Column(
        name = "token",
        nullable = false,
        unique = true
    )
    private String token;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(
        name = "moderator",
        nullable = false
    )
    private boolean moderator;

    public User() {
    }

    /**
     * User constructor.
     *
     * @param id user id
     * @param nick user name
     * @param token user token
     * @param room room object
     * @param moderator moderator status
     */
    public User(long id, String nick, String token, Room room, boolean moderator) {
        this.id = id;
        this.nick = nick;
        this.token = token;
        this.room = room;
        this.moderator = moderator;
    }

    /**
     * Getter to get user id.
     *
     * @return user id
     */
    public long getId() {
        return id;
    }

    /**
     * Setter to set user id.
     *
     * @param id new user id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter to get user name.
     *
     * @return user name
     */
    public String getNick() {
        return nick;
    }

    /**
     * Setter to set/change user name.
     *
     * @param nick new user name
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * Getter to get user token.
     *
     * @return user token
     */
    public String getToken() {
        return token;
    }

    /**
     * Setter to set user token.
     *
     * @param token new token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Getter to get room.
     *
     * @return room object
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Setter to set room.
     *
     * @param room new room object
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * Setter to set/change moderator status.
     *
     * @param moderator true if moderator, false if not
     */
    public void setModerator(boolean moderator) {
        this.moderator = moderator;
    }

    /**
     * Getter to get moderator status.
     *
     * @return true if moderator, false if not
     */
    public boolean getModerator() {
        return moderator;
    }

    /**
     * Makes user object into string overview.
     *
     * @return text overview of user object
     */
    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", nick='" + nick + '\''
                + ", token='" + token + '\''
                + ", room=" + room
                + ", moderator=" + moderator
                + '}';
    }

    /**
     * Checks if user is equal to any object.
     *
     * @param o object to compare with
     * @return true if equal, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;

        if (id != user.id) {
            return false;
        }
        if (!Objects.equals(nick, user.nick)) {
            return false;
        }
        if (!Objects.equals(token, user.token)) {
            return false;
        }
        if (moderator != user.moderator) {
            return false;
        }
        return Objects.equals(room, user.room);
    }
}
