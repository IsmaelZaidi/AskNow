package nl.tudelft.oopp.g72.models;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;


@Entity(name = "User")
@Table(name = "user")
public class User {
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
        updatable = false
    )
    private long id;

    @Column(
        name = "nick",
        nullable = false
    )
    private String nick;

    @Column(
        name = "token",
        nullable = false
    )
    private String token;

    public User() {
    }


    public User(String nick, String token) {
        this.nick = nick;
        this.token = token;
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

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", nick='" + nick + '\''
                + ", token='" + token + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (id != user.id) {
            return false;
        }
        if (!nick.equals(user.nick)) {
            return false;
        }
        return token.equals(user.token);
    }

}
