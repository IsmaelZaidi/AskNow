package nl.tudelft.oopp.g72;

import nl.tudelft.oopp.g72.models.MessageDelete;
import nl.tudelft.oopp.g72.models.MessageUpvote;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MessageUpvoteTest {
    MessageUpvote message = new MessageUpvote(1,10);


    @Test
    public void constructorTest() {
        MessageUpvote message2 = new MessageUpvote(2,20);
        Assertions.assertNotNull(message2);
    }

    @Test
    public void getUpvotesTest() {
        Assertions.assertEquals(10, message.getUpvotes());
    }

    @Test
    public void setUpvotesTest() {
        MessageUpvote message2 = new MessageUpvote(2,20);
        message2.setUpvotes(40);
        Assertions.assertEquals(40, message2.getUpvotes());
    }

    @Test
    public void getQuestionIdTest() {
        Assertions.assertEquals(1, message.getQuestionId());
    }

    @Test
    public void setQuestionIdTest() {
        MessageUpvote message2 = new MessageUpvote(1,10);
        message2.setQuestionId(3);
        Assertions.assertEquals(3, message2.getQuestionId());
    }
}
