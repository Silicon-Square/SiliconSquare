package it.siliconsquare.model.administration;

import javax.persistence.*;

@Entity
@Table(name = "topic_issues")
public class TopicIssues {

    @Id
    @Column(name = "id_topic", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idTopic;

    @Column(name = "topic")
    private String topic;

    public TopicIssues() {
    }

    public int getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(int idTopic) {
        this.idTopic = idTopic;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
