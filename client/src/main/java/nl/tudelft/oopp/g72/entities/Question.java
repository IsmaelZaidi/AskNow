package nl.tudelft.oopp.g72.entities;

public class Question {
    private User user;

    private String text;

    private int upvotes;

    private boolean answered;

    private String answer;

    private long time;

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

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    /**
     * Constructor.
     * @param user user who asked the q
     * @param text text of the q
     * @param upvotes upvotes of the q
     * @param time when was the question asked
     */
    public Question(User user, String text, int upvotes, long time) {
        this.user = user;
        this.text = text;
        this.upvotes = upvotes;
        this.time = time;
    }
}
