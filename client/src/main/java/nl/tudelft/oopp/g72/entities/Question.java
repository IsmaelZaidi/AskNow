package nl.tudelft.oopp.g72.entities;

public class Question {
    private User user;
    private String text;
    private int upvotes;

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

    public Question(User user, String text, int upvotes) {
        this.user = user;
        this.text = text;
        this.upvotes = upvotes;
    }
}
