package nl.tudelft.oopp.g72.models;

public class MessageDelete {
    private Long questionId;

    /**
     * Message constructor.
     * @param questionId id of question
     */
    public MessageDelete(Long questionId) {
        this.questionId = questionId;
    }
}
