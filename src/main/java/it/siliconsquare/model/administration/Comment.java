package it.siliconsquare.model.administration;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @Column(name = "id_comment", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idComment;

    @JoinColumn(name = "id_user")
    @ManyToOne
    private User user;

    @Column(name = "comment")
    private String comment;

    @JoinColumn(name = "id_post")
    @ManyToOne
    private Post post;

    public Comment() {
    }

    public Comment(Post post, User user, String comment, int idComment) {
        this.post = post;
        this.user = user;
        this.comment = comment;
        this.idComment = idComment;
    }

    public Post getPost() {
        return post;
    }

    public int getIdPost() {
        return post.getIdPost();
    }

    public User getUser() {
        return user;
    }

    public int getIdUser() {
        return user.getIdUser();
    }

    public String getComment() {
        return comment;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "idComment=" + idComment +
                ", idUser=" + user.getIdUser() +
                ", comment='" + comment + '\'' +
                ", idPost=" + post.getIdPost() +
                '}';
    }
}
