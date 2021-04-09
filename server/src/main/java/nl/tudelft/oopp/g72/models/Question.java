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

/**
 * Class handling Question object.
 */
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
     * Question constructor.
     *
     * @param id user id
     * @param user user entity
     * @param room room entity
     * @param text text content
     * @param timestamp time when created
     * @param upvotes amount of upvotes
     * @param answer answer text
     * @param answered whether answered or not
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

    /**
     * Getter to get question id.
     *
     * @return question id
     */
    public long getId() {
        return id;
    }

    /**
     * Setter to set/change question id.
     *
     * @param id new question id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter to get user.
     *
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter to set/change user.
     *
     * @param user new user of question
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Getter to get room.
     *
     * @return room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Setter to set/change room.
     *
     * @param room new room
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * Getter to get text of question.
     *
     * @return text
     */
    public String getText() {
        return text;
    }

    /**
     * Setter to set/change text of question.
     *
     * @param text new text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Getter to get timestamp of question.
     *
     * @return timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Setter to set/change timestamp of question.
     *
     * @param timestamp new timestamp
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Getter to get number of upvotes of question.
     *
     * @return number of upvotes
     */
    public int getUpvotes() {
        return upvotes;
    }

    /**
     * Setter to set/change number of upvotes of question.
     *
     * @param upvotes new number of upvotes
     */
    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    /**
     * Getter to get answer.
     *
     * @return answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Setter to set/change answer.
     *
     * @param answer new answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Check if question is answered.
     *
     * @return true if answered, false if unanswered
     */
    public boolean isAnswered() {
        return answered;
    }

    /**
     * Setter to set question answered or unanswered.
     *
     * @param answered true for answered, false for unanswered
     */
    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    /**
     * Makes question object to proper text overview.
     *
     * @return string displaying question object
     */
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

    /**
     * Method to compare Question object to different object.
     *
     * @param o object you're comparing to
     * @return true if equal, false if unequal
     */
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
