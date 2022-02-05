package it.siliconsquare.model.administration;

import javax.persistence.*;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @Column(name = "id_notification", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idNotification;

    @JoinColumn(name = "id_sender")
    @ManyToOne
    private User sender;

    @Column(name = "notification_title")
    private String notificationTitle;

    @Column(name = "notification_details")
    private String notificationDetails;

    @JoinColumn(name = "id_receiver")
    @ManyToOne
    private User receiver;

    @JoinColumn(name = "id_post")
    @ManyToOne
    private Post post;

    public Notification() {
    }

    public Notification(int idNotification, User sender, String notificationTitle, String notificationDetails, User receiver, Post post) {
        this.idNotification = idNotification;
        this.sender = sender;
        this.notificationTitle = notificationTitle;
        this.notificationDetails = notificationDetails;
        this.receiver = receiver;
        this.post = post;
    }

    public int getIdNotification() {
        return idNotification;
    }

    public User getSender() {
        return sender;
    }

    public int getIdSender() {
        return sender.getIdUser();
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public String getNotificationDetails() {
        return notificationDetails;
    }

    public User getReceiver() {
        return receiver;
    }

    public int getIdReceiver() {
        return receiver.getIdUser();
    }

    public Post getPost() {
        return post;
    }

    public int getIdPost() {
        return post.getIdPost();
    }

    public void setIdNotification(int idNotification) {
        this.idNotification = idNotification;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public void setNotificationDetails(String notificationDetails) {
        this.notificationDetails = notificationDetails;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "idNotification=" + idNotification +
                ", idSender=" + sender.getIdUser() +
                ", notificationTitle='" + notificationTitle + '\'' +
                ", notificationDetails='" + notificationDetails + '\'' +
                ", idReceiver=" + receiver.getIdUser() +
                ", idPost=" + post.getIdPost() +
                '}';
    }
}
