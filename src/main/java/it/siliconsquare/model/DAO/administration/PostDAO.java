package it.siliconsquare.model.DAO.administration;

import it.siliconsquare.model.administration.Post;
import it.siliconsquare.model.administration.User;

import java.util.List;

public interface PostDAO {
    public List<Post> getAllPost();
    Post getPostById(int id);
    
    Post getLastPostOfUser(User user);
    public boolean updateExistingPost(Post p);

    boolean savePost(Post post);
    boolean publishPost(Post post);

    boolean deletePost(Post post);

    boolean updatePost(Post post);

    public void fetchMissingParameters(Post post, int user);

    List<Post> getAllUserPosts(int user);
    List<Post> getAllUserPosts(int user, int actualPost);

    public List<Post> getOtherUsersPosts(User u);
    public List<Post> getOtherUsersPosts(User u, int actualPost);
     
}
