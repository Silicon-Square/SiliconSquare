package it.siliconsquare.model.DAO.administration;

import it.siliconsquare.model.administration.Like;
import it.siliconsquare.model.administration.Post;

import java.util.List;

public interface LikeDAO {
    List<Like> getAllLike();
 
    Like getLikeById(int id);
    boolean getExistingLike(int id_user, int id_post);

    void saveLike(Like like);
    boolean hitLike(Like like);

    boolean updateLikeCount(int id_post);

    void deleteLike(Like like);
    boolean removeLike(Like like);

    void updateLike(Like like);

    int likeToPost(Post post);
}
