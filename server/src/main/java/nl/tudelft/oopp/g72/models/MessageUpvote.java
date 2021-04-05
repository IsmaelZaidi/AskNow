package nl.tudelft.oopp.g72.models;

public class MessageUpvote {
    private long questionId;
    private int upvotes;

    public MessageUpvote() {
    }

    /**
     * Message constructor.
     * @param questionId question id
     * @param upvotes number of upvotes
     */
    public MessageUpvote(long questionId, int upvotes) {
        this.questionId = questionId;
        this.upvotes = upvotes;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }
}
