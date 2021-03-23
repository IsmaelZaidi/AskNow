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

@Entity(name = "User")
@Table(name = "user")
public class  User {
    @JsonIgnore
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
     * Constructor.
     * @param id long
     * @param nick String
     * @param token String
     * @param room room
     * @param moderator moderator
     */
    public User(long id, String nick, String token, Room room, boolean moderator) {
        this.id = id;
        this.nick = nick;
        this.token = token;
        this.room = room;
        this.moderator = moderator;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setModerator(boolean moderator) {
        this.moderator = moderator;
    }

    public boolean getModerator() {
        return moderator;
    }

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
