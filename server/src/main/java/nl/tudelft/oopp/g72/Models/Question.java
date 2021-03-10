package nl.tudelft.oopp.g72.Models;

import java.util.Objects;
import javax.persistence.*;


@Entity(name = "Question")
@Table(name = "Question")
public class Question {
    @Id
    @SequenceGenerator(
            name = "question_id_sequence",
            sequenceName = "question_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "question_id_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private nl.tudelft.oopp.g72.models.User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @Column(
            name = "timestamp",
            updatable = false
    )
    private String timestamp;

    @Column(
            name = "upvotes",
            updatable = false
    )
    private int upvotes;

    @Column(
            name = "answer",
            updatable = false
    )
    private String answer;

    @Column(
            name = "answered",
            updatable = false
    )
    private boolean answered;

    public Question() {

    }

    public Question(String timestamp, int upvotes, String answer) {
        this.timestamp = timestamp;
        this.upvotes = upvotes;
        this.answer = answer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    @Override
    public String toString() {
        return "Question{"
                + "id=" + id
                + ", timestamp='" + timestamp + '\''
                + ", upvotes=" + upvotes
                + ", answer='" + answer + '\''
                + ", answered=" + answered
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Question)) {
            return false;
        }
        Question question = (Question) o;
        return id == question.id
                && upvotes == question.upvotes
                && answered == question.answered
                && Objects.equals(timestamp, question.timestamp)
                && Objects.equals(answer, question.answer);
    }


}
