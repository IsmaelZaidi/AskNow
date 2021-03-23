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

    public User() {
    }

    /**
     * Constructor.
     * @param id long
     * @param nick String
     * @param token String
     * @param room room
     */
    public User(long id, String nick, String token, Room room) {
        this.id = id;
        this.nick = nick;
        this.token = token;
        this.room = room;
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

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", nick='" + nick + '\''
                + ", token='" + token + '\''
                + ", room=" + room
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
        return Objects.equals(room, user.room);
    }
}
