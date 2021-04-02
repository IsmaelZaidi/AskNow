package nl.tudelft.oopp.g72.entities;

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

    public String getMerge() { return merge; }

    public void setMerge(String merge) { this.merge = merge; }

    public String getEdit() { return edit; }

    public void setEdit(String edit) { this.edit = edit; }

    public String getRemove() { return remove; }

    public void setRemove(String remove) { this.remove = remove; }

    public String getAnswered() { return answered1; }

    public void setAnswered(String answered1) { this.answered1 = answered1; }

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
                    int upvotes, String answer, boolean answered, String merge, String edit, String remove) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.timestamp = timestamp;
        this.upvotes = upvotes;
        this.answer = answer;
        this.answered = answered;
        this.merge = merge;
        this.edit = edit;
        this.remove = remove;
    }
}
