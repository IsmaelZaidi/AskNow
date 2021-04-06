package nl.tudelft.oopp.g72.entities;

public class MessageDelete {
    private long questionId;

    public MessageDelete() {
    }

    /**
     * Message constructor.
     * @param questionId id of question
     */
    public MessageDelete(long questionId) {
        this.questionId = questionId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
}
