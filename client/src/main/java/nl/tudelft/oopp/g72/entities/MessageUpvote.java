package nl.tudelft.oopp.g72.entities;

/**
 * Class holding functionality regarding upvoting questions.
 */
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

    /**
     * Getter to get id of a question.
     *
     * @return the id of a question
     */
    public long getQuestionId() {
        return questionId;
    }

    /**
     * Setter to set the id of a question.
     *
     * @param questionId the new question id
     */
    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    /**
     * Getter to get the upvotes on a question.
     *
     * @return the upvotes on a question
     */
    public int getUpvotes() {
        return upvotes;
    }

    /**
     * Setter to set the upvotes on a question.
     *
     * @param upvotes the new number of upvotes.
     */
    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }
}
