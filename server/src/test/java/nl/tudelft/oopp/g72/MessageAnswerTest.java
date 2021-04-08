package nl.tudelft.oopp.g72;

import nl.tudelft.oopp.g72.models.MessageAnswer;
import nl.tudelft.oopp.g72.models.MessageDelete;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MessageAnswerTest {
    MessageAnswer message1 = new MessageAnswer(5, true, "Yes");


    @Test
    public void constructorTest() {
        MessageAnswer message2 = new MessageAnswer(5, true, "Yes");
        Assertions.assertNotNull(message2);
    }

    @Test
    public void getQuestionIdTest() {
        Assertions.assertEquals(5, message1.getQuestionId());
    }

    @Test
    public void setQuestionIdTest() {
        MessageAnswer message2 = new MessageAnswer(5, true, "Yes");
        message2.setQuestionId(3);
        Assertions.assertEquals(3, message2.getQuestionId());
    }

    @Test
    public void isAnsweredTest() {
        Assertions.assertEquals(true, message1.isAnswered());
    }

    @Test
    public void setAnsweredTest() {
        message1.setAnswered(false);
        Assertions.assertEquals(false, message1.isAnswered());
    }

    @Test
    public void getAnswerTest() {
        Assertions.assertEquals("Yes", message1.getAnswer());
    }

    @Test
    public void setAnswerTest() {
        MessageAnswer message2 = new MessageAnswer(5, true, "Yes");
        message2.setAnswer("Hello");
        Assertions.assertEquals("Hello", message2.getAnswer());
    }


}


