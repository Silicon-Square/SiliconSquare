package it.siliconsquare.model.administration;

import it.siliconsquare.model.configuration.Configuration;

import javax.persistence.*;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_post", nullable = false)
    private int idPost;

    @Column(name = "post_title", nullable = false)
    private String postTitle;

    @Column(name = "post_description", nullable = false)
    private String postDescription;

    private int choice;

    @Column(name = "poll_option_one")
    private String pollOptionOne;

    @Column(name = "poll_option_two")
    private String pollOptionTwo;

    @Column(name = "poll_option_one_choices")
    private int pollOptionOneChoices;

    @Column(name = "poll_option_two_choices")
    private int pollOptionTwoChoices;

    @Column(name = "post_picture")
    private String postPicture;

    @JoinColumn(name = "post_configuration")
    @ManyToOne
    private Configuration postConfiguration;

    @Column(name = "id_user")
    private int idUser;

    @JoinColumn(name = "post_user")
    @ManyToOne
    private User postUser;

    @Column(name = "like_count")
    private int likeCount;

    private boolean like;

    public Post() {
    }

    public Post(int idPost, String postTitle, String postDescription, String pollOptionOne,
            String pollOptionTwo, int pollOptionOneChoice, int pollOptionTwoChoice, String postPicture,
            Configuration postConfiguration,
            int idUser, User postUser, int likeCount) {
        this.idPost = idPost;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.pollOptionOne = pollOptionOne;
        this.pollOptionTwo = pollOptionTwo;
        this.pollOptionOneChoices = pollOptionOneChoice;
        this.pollOptionTwoChoices = pollOptionTwoChoice;
        this.postPicture = postPicture;
        this.postConfiguration = postConfiguration;
        this.idUser = idUser;
        this.postUser = postUser;
        this.likeCount = likeCount;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getPollOptionOne() {
        return pollOptionOne;
    }

    public void setPollOptionOne(String pollOptionOne) {
        this.pollOptionOne = pollOptionOne;
    }

    public String getPollOptionTwo() {
        return pollOptionTwo;
    }

    public void setPollOptionTwo(String pollOptionTwo) {
        this.pollOptionTwo = pollOptionTwo;
    }

    public int getPollOptionOneChoices() {
        return pollOptionOneChoices;
    }

    public void setPollOptionOneChoices(int pollOptionOneChoices) {
        this.pollOptionOneChoices = pollOptionOneChoices;
    }

    public int getPollOptionTwoChoices() {
        return pollOptionTwoChoices;
    }

    public void setPollOptionTwoChoices(int pollOptionTwoChoices) {
        this.pollOptionTwoChoices = pollOptionTwoChoices;
    }

    public String getPostPicture() {
        return postPicture;
    }

    public void setPostPicture(String postPicture) {
        this.postPicture = postPicture;
    }

    public Configuration getPostConfiguration() {
        return postConfiguration;
    }

    public void setPostConfiguration(Configuration postConfiguration) {
        this.postConfiguration = postConfiguration;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public User getPostUser() {
        return postUser;
    }

    public void setPostUser(User postUser) {
        this.postUser = postUser;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    @Override
    public String toString() {
        return "Post [choice=" + choice + ", idPost=" + idPost + ", idUser=" + idUser + ", like=" + like
                + ", likeCount=" + likeCount + ", pollOptionOne=" + pollOptionOne + ", pollOptionOneChoices="
                + pollOptionOneChoices + ", pollOptionTwo=" + pollOptionTwo + ", pollOptionTwoChoices="
                + pollOptionTwoChoices + ", postConfiguration=" + postConfiguration + ", postDescription="
                + postDescription + ", postPicture=" + postPicture + ", postTitle=" + postTitle + ", postUser="
                + postUser + "]";
    }
}
