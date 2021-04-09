package nl.tudelft.oopp.g72.models;

/**
 * Class handling upvoting messages.
 */
public class MessageUpvote {
    private long questionId;
    private int upvotes;

    public MessageUpvote() {
    }

    /**
     * Message constructor.
     *
     * @param questionId question id
     * @param upvotes number of upvotes
     */
    public MessageUpvote(long questionId, int upvotes) {
        this.questionId = questionId;
        this.upvotes = upvotes;
    }

    /**
     * Getter to get question id.
     *
     * @return question id
     */
    public long getQuestionId() {
        return questionId;
    }

    /**
     * Setter to set/change question id.
     *
     * @param questionId question id
     */
    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    /**
     * Getter to get amount of upvotes.
     *
     * @return amount of upvotes
     */
    public int getUpvotes() {
        return upvotes;
    }

    /**
     * Setter to set/change amount of upvotes.
     *
     * @param upvotes new amount of upvotes
     */
    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }
}
