package nl.tudelft.oopp.g72.models;

/**
 * Class handling answering messages
 */
public class MessageAnswer {
    private long questionId;
    private boolean answered;
    private String answer;

    public MessageAnswer() {
    }

    /**
     * Message constructor.
     *
     * @param questionId question id
     * @param answered true if question is answered
     * @param answer answer content string
     */
    public MessageAnswer(long questionId, boolean answered, String answer) {
        this.questionId = questionId;
        this.answered = answered;
        this.answer = answer;
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
     * Setter to set question id.
     *
     * @param questionId new question id
     */
    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    /**
     * Check if question is answered.
     *
     * @return boolean
     */
    public boolean isAnswered() {
        return answered;
    }

    /**
     * Set question answered or not.
     *
     * @param answered true if answered, false if not
     */
    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    /**
     * Getter to get answer to question.
     *
     * @return answer to question
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Setter to set/change answer to question.
     *
     * @param answer new answer to question
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
