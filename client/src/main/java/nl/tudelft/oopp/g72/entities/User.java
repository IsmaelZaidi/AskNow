package nl.tudelft.oopp.g72.entities;

public class User {
    private long id;

    private String nick;

    private boolean moderator;

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

    public boolean isModerator() {
        return moderator;
    }

    public void setModerator(boolean moderator) {
        this.moderator = moderator;
    }

    public User() {

    }

    /**
     * Creates user entity.
     * @param id id of user
     * @param nick nickname
     * @param moderator if it is moderator
     */
    public User(long id, String nick, boolean moderator) {
        this.id = id;
        this.nick = nick;
        this.moderator = moderator;
    }
}
