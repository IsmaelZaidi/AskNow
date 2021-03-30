package nl.tudelft.oopp.g72.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
            nullable = false,
            unique = true
    )
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(
            name = "text",
            nullable = false
    )
    private String text;

    @Column(
            name = "timestamp",
            nullable = false
    )
    private long timestamp;

    @Column(
            name = "upvotes",
            nullable = false
    )
    private int upvotes;

    @Column(
            name = "answer"
    )
    private String answer;

    @Column(
            name = "answered",
            nullable = false
    )
    private boolean answered;

    public Question() {

    }

    /**
     * Constructor.
     * @param id long , autoset
     * @param user User entity
     * @param room Room entity
     * @param text String
     * @param timestamp long time in POSIX time
     * @param upvotes integer
     * @param answer String
     * @param answered boolean
     */
    public Question(long id, User user, Room room, String text,
                    long timestamp, int upvotes, String answer, boolean answered) {
        this.id = id;
        this.user = user;
        this.room = room;
        this.text = text;
        this.timestamp = timestamp;
        this.upvotes = upvotes;
        this.answer = answer;
        this.answered = answered;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
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
                + ", user=" + user
                + ", room=" + room
                + ", text=" + text
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

        if (id != question.id) {
            return false;
        }
        if (upvotes != question.upvotes) {
            return false;
        }
        if (answered != question.answered) {
            return false;
        }
        if (!Objects.equals(user, question.user)) {
            return false;
        }
        if (!Objects.equals(room, question.room)) {
            return false;
        }
        if (!Objects.equals(text, question.text)) {
            return false;
        }
        if (!Objects.equals(timestamp, question.timestamp)) {
            return false;
        }
        return Objects.equals(answer, question.answer);
    }

}
