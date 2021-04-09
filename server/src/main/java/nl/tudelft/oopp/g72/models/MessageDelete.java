package nl.tudelft.oopp.g72.models;

/**
 * Class to handle deleting messages.
 */
public class MessageDelete {
    private long questionId;

    public MessageDelete() {
    }

    /**
     * Message constructor.
     *
     * @param questionId question id
     */
    public MessageDelete(long questionId) {
        this.questionId = questionId;
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
     * @param questionId new question id
     */
    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
}
