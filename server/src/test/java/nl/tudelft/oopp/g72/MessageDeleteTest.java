package nl.tudelft.oopp.g72;

import nl.tudelft.oopp.g72.models.MessageAnswer;
import nl.tudelft.oopp.g72.models.MessageDelete;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MessageDeleteTest {
    MessageDelete message = new MessageDelete(6);


    @Test
    public void constructorTest() {
        MessageDelete message3 = new MessageDelete(2);
        Assertions.assertNotNull(message3);
    }

    @Test
    public void getQuestionIdTest() {
        Assertions.assertEquals(6, message.getQuestionId());
    }

    @Test
    public void setQuestionIdTest() {
        MessageDelete message2 = new MessageDelete(5);
        message2.setQuestionId(3);
        Assertions.assertEquals(3, message2.getQuestionId());
    }
}
