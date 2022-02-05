package it.siliconsquare.model.administration;

import javax.persistence.*;

@Entity
@Table(name = "poll_choice")
public class PollChoice {

    public static final int NOCHOICE = -1;
    public static final int OPTION1 = 1;
    public static final int OPTION2 = 2;
 
    @Id
    @Column(name = "id_poll_choice", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idPollChoice;

    @JoinColumn(name = "id_post")
    @ManyToOne
    private Post post;

    @JoinColumn(name = "id_user")
    @ManyToOne
    private User user;
    
    @Column(name = "choice")
    private int choice;

    public PollChoice() {
    }

    public int getIdPollChoice() {
        return idPollChoice;
    }

    public void setIdPollChoice(int idPollChoice) {
        this.idPollChoice = idPollChoice;
    }

    public int getIdPost() {
        return post.getIdPost();
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getIdUser() {
        return user.getIdUser();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    @Override
    public String toString() {
        return "PollChoice [choice=" + choice + ", idPollChoice=" + idPollChoice + ", post=" + post + ", user=" + user
                + "]";
    }

    
}