package nl.tudelft.oopp.g72.entities;

/**
 * Class holding functionality regarding answering questions.
 */
public class MessageAnswer {
    private long questionId;
    private boolean answered;
    private String answer;

    public MessageAnswer() {
    }

    /**
     * Message contructor.
     * @param questionId id of question
     * @param answered true if question is answered
     * @param answer string of the answer
     */
    public MessageAnswer(long questionId, boolean answered, String answer) {
        this.questionId = questionId;
        this.answered = answered;
        this.answer = answer;
    }

    /**
     * Getter for the id of the question.
     *
     * @return the id of the question
     */
    public long getQuestionId() {
        return questionId;
    }

    /**
     * Setter to change/set the id of the question.
     *
     * @param questionId the id of the question
     */
    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    /**
     * Method to check if a method has been answered.
     *
     * @return boolean value stating whether a question is answered or not
     */
    public boolean isAnswered() {
        return answered;
    }

    /**
     * Method to mark question answered.
     *
     * @param answered boolean value to set question answered
     */
    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    /**
     * Method to get the answer of the question.
     *
     * @return the answer to the question
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Method to set the answer to a question.
     *
     * @param answer the answer to the question
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
