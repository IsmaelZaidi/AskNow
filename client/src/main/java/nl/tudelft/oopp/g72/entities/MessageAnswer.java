package nl.tudelft.oopp.g72.entities;

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

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
