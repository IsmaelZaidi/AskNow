package nl.tudelft.oopp.g72.entities;

/**
 * Class holding functionality regarding user.
 */
public class User {
    private long id;

    private String nick;

    private boolean moderator;

    /**
     * Getter to get id.
     *
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Setter to set id.
     *
     * @param id new id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter to get nickname.
     *
     * @return nickname
     */
    public String getNick() {
        return nick;
    }

    /**
     * Setter to set nickname.
     *
     * @param nick new nickname
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * Checks whether user is moderator or not.
     *
     * @return boolean
     */
    public boolean isModerator() {
        return moderator;
    }

    /**
     * Sets user moderator or not.
     *
     * @param moderator boolean
     */
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
