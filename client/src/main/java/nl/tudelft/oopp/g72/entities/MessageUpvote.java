package nl.tudelft.oopp.g72.entities;

public class MessageUpvote {
    private Long questionId;
    private int upvotes;

    /**
     * Message constructor.
     * @param questionId question id
     * @param upvotes number of upvotes
     */
    public MessageUpvote(Long questionId, int upvotes) {
        this.questionId = questionId;
        this.upvotes = upvotes;
    }
}
