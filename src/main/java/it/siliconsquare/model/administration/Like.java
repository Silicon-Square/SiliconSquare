package it.siliconsquare.model.administration;

import javax.persistence.*;

@Entity
@Table(name = "public.like")
public class Like {

    @Id
    @Column(name = "id_like", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idLike;

    @Column(name = "id_post")
    private int post;

    @JoinColumn(name = "id_user")
    @ManyToOne
    private User user;

    public Like() {
    }

    public Like(int post, User user, int idLike) {
        this.post = post;
        this.user = user;
        this.idLike = idLike;
    }

    public int getPost() {
        return post;
    }

    public int getIdPost() {
        return post;
    }

    public User getUser() {
        return user;
    }

    public int getIdUser() {
        return user.getIdUser();
    }

    public int getIdLike() {
        return idLike;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setIdLike(int idLike) {
        this.idLike = idLike;
    }

    @Override
    public String toString() {
        return "Like{" +
                "idLike=" + idLike +
                ", idPost=" + post +
                ", idUser=" + user.getIdUser() +
                '}';
    }
}
