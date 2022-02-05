package it.siliconsquare.model.DAO.Impl.administration;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import it.siliconsquare.model.DAO.administration.CommentDAO;
import it.siliconsquare.model.administration.Comment;
import it.siliconsquare.model.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentDAOImpl implements CommentDAO {
    @Autowired
    private List<Configuration> configurationList;
    private String sql = "SELECT * FROM comment ";

    public CommentDAOImpl() {
    }

    @Override
    public List<Comment> getAllComment() {
        return Q2ObjList.fromSelect(Comment.class, sql);
    }

    @Override
    public Comment getCommentById(int id) {
        return Q2Obj.fromSelect(Comment.class, sql + " WHERE id_comment = ?", id);
    }

    @Override
    public void saveComment(Comment comment) {
        Q2Obj.insert(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        Q2Obj.delete(comment);
    }

    @Override
    public void updateComment(Comment comment) {
        Q2Obj.update(comment);
    }
}
