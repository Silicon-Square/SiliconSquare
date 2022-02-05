package it.siliconsquare.model.DAO.Impl.administration;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.logger.Logger;
import it.siliconsquare.model.DAO.administration.LikeDAO;
import it.siliconsquare.model.administration.Like;
import it.siliconsquare.model.administration.Post;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class LikeDAOImpl implements LikeDAO {

    @Autowired
    private List<Like> likeList;
    private String sql = "SELECT * FROM public.like, public.user WHERE public.like.id_user = public.user.id_user ";

    public LikeDAOImpl() {
        likeList = new ArrayList<>();
    }

    @Override
    public List<Like> getAllLike() {
        return Q2ObjList.fromSelect(Like.class, sql);
    }

    @Override
    public Like getLikeById(int id) {
        return Q2Obj.fromSelect(Like.class, sql + "AND id_like = ?", id);
    }

    @Override
    public boolean getExistingLike(int id_user, int id_post) {
        return Q2Obj.fromSelect(Like.class, sql + "AND public.like.id_user = ? AND public.like.id_post = ?;", id_user, id_post) != null;
    }

    @Override
    public void saveLike(Like like) {
        Q2Obj.insert(like);
    }

    @Override
    public boolean hitLike(Like like) {
        if(getExistingLike(like.getIdUser(), like.getPost()))
            return removeLike(like);

        String query = "INSERT INTO public.like (id_post, id_user) VALUES(?,?);";

        int affectedRows = Q2Sql.executeUpdate(query, like.getIdPost(), like.getIdUser());

        String logMessage = "Somebody hit like [id post, id user] - " + like.getIdPost() + "," + like.getIdUser();

        if (affectedRows == 0)
            logMessage = "Attempt to hit like [id post, id user] - " + like.getIdPost() + "," + like.getIdUser();

        Logger.getInstance().captureMessage(logMessage);

        if (affectedRows > 0){
            updateLikeCount(like.getIdPost());
            return true;
        }

        return false;
    }

    @Override
    public void deleteLike(Like like) {
        Q2Obj.delete(like);
    }

    @Override
    public boolean removeLike(Like like) {
        String query = "DELETE FROM public.like WHERE id_post=? AND id_user=?;";


        int affectedRows = Q2Sql.executeUpdate(query, like.getIdPost(), like.getIdUser());

        String logMessage = "Somebody removed like [id post, id user] - " + like.getIdPost() + "," + like.getIdUser();

        if (affectedRows == 0)
            logMessage = "Attempt to remove like [id post, id user] - " + like.getIdPost() + "," + like.getIdUser();

        Logger.getInstance().captureMessage(logMessage);

        if (affectedRows > 0){
            updateLikeCount(like.getIdPost());
            return true;
        }

        return false;
    }

    public boolean updateLikeCount(int id_post){
        String sqlModifyPost = "UPDATE post as p1 SET like_count = (SELECT count(*) FROM public.like WHERE id_post = p1.id_post and id_post = ?) WHERE p1.id_post = ?;";

        int affectedRows = Q2Sql.executeUpdate(sqlModifyPost, id_post, id_post);

        String logMessage = "Like increment [Id post] - " + id_post;

        if (affectedRows == 0)
            logMessage = "Attempt to increment like [Id post] - " + id_post;

        Logger.getInstance().captureMessage(logMessage);

        return affectedRows > 0;
    }

    @Override
    public void updateLike(Like like) {
        Q2Obj.update(like);
    }

    @Override
    public int likeToPost(Post post) {
        String query = "SELECT count(*) FROM public.like WHERE public.like.id_post = ?;";

        if(post == null)
            return 0;

        return Q2Sql.numberFromSql( query, post.getIdPost()).intValue();
    }
}