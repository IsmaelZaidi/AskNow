package nl.tudelft.oopp.g72.models;

public class MessageAnswer {
    private Long questionId;
    private boolean answered;
    private String answer;

    /**
     * Message contructor.
     * @param questionId id of question
     * @param answered true if question is answered
     * @param answer string of the answer
     */
    public MessageAnswer(Long questionId, boolean answered, String answer) {
        this.questionId = questionId;
        this.answered = answered;
        this.answer = answer;
    }
}
