package nl.tudelft.oopp.g72.entities;

public class Question {
    private long id;

    private User user;

    private String text;

    private long timestamp;

    private int upvotes;

    private String answer;

    private boolean answered;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
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
                    int upvotes, String answer, boolean answered) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.timestamp = timestamp;
        this.upvotes = upvotes;
        this.answer = answer;
        this.answered = answered;
    }
}
