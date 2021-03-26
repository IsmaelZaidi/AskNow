package nl.tudelft.oopp.g72.entities;

public class Question {
    private User user;

    private String text;

    private int upvotes;

    private boolean answered;

    private String answer;

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

    /**
     * Constructor.
     * @param user user who asked the q
     * @param text text of the q
     * @param upvotes upvotes of the q
     */
    public Question(User user, String text, int upvotes) {
        this.user = user;
        this.text = text;
        this.upvotes = upvotes;
    }
}
