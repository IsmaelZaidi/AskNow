package nl.tudelft.oopp.g72.models;

public class MessageUpvote {
    private Long questionId;
    private int upvotes;

    public MessageUpvote(Long questionId) {
        this.questionId = questionId;
    }
}
