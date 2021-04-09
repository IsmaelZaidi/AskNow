package nl.tudelft.oopp.g72.entities;

/**
 * Class holding functionality regarding deleting questions.
 */
public class MessageDelete {
    private long questionId;

    public MessageDelete() {
    }

    /**
     * Message constructor.
     *
     * @param questionId id of question
     */
    public MessageDelete(long questionId) {
        this.questionId = questionId;
    }

    /**
     * Getter to get the id of the question.
     *
     * @return the id of the question
     */
    public long getQuestionId() {
        return questionId;
    }

    /**
     * Setter to change the id of the question.
     *
     * @param questionId the new id of the question
     */
    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
}
