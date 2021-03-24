package nl.tudelft.oopp.g72.entities;

public class User {
    private long id;
    private String nick;

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

    public User(long id, String nick) {
        this.id = id;
        this.nick = nick;
    }
}
