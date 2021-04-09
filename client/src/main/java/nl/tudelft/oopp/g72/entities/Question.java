package nl.tudelft.oopp.g72.entities;

/**
 * Class holding functionality regarding questions.
 */
public class Question {
    private long id;

    private User user;

    private String text;

    private long timestamp;

    private int upvotes;

    private String answer;

    private boolean answered;

    private String merge;

    private String edit;

    private String remove;

    private String answered1;

    /**
     * Getter to get the id of a question.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Setter to set a new id to a question.
     *
     * @param id the new id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter to get the user of a question.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter to set a user to a question.
     *
     * @param user new user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Getter to get the text of a question.
     *
     * @return text
     */
    public String getText() {
        return text;
    }

    /**
     * Setter to set the text of a question.
     *
     * @param text new text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Getter to get the timestamp of a question.
     *
     * @return timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Setter to get the timestamp of a question.
     *
     * @param timestamp new timestamp
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Getter to get upvotes to a question.
     *
     * @return upvotes
     */
    public int getUpvotes() {
        return upvotes;
    }

    /**
     * Setter to set upvotes of a question.
     *
     * @param upvotes new upvotes
     */
    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    /**
     * Getter to get answer to a question.
     *
     * @return answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Setter to set answer to a question.
     *
     * @param answer new answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Returns boolean value stating whether the question is answered or not.
     *
     * @return boolean value
     */
    public boolean isAnswered() {
        return answered;
    }

    /**
     * Sets a question answered or not.
     *
     * @param answered boolean value
     */
    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    /**
     * Returns the string edit of a question.
     *
     * @return edit
     */
    public String getEdit() {
        return edit;
    }

    /**
     * Setter to set string edit of a question.
     *
     * @param edit new edit
     */
    public void setEdit(String edit) {
        this.edit = edit;
    }

    /**
     * Getter to get string remove of a question.
     *
     * @return remove
     */
    public String getRemove() {
        return remove;
    }

    /**
     * Setter to set string remove of a question.
     *
     * @param remove remove
     */
    public void setRemove(String remove) {
        this.remove = remove;
    }

    public Question() {

    }

    /**
     * Question constructor.
     * @param id question id
     * @param user user who asked
     * @param text text of question
     * @param timestamp time question was asked
     * @param upvotes how many upvoted
     * @param answer text of the answer
     * @param answered if it was answered
     */
    public Question(long id, User user, String text, long timestamp,
                    int upvotes, String answer, boolean answered, String merge,
                    String edit, String remove) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.timestamp = timestamp;
        this.upvotes = upvotes;
        this.answer = answer;
        this.answered = answered;
        this.edit = edit;
        this.remove = remove;
    }
}
