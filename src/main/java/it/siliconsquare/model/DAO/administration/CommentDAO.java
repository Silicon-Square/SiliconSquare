package it.siliconsquare.model.DAO.administration;

import it.siliconsquare.model.administration.Comment;

import java.util.List;

public interface CommentDAO {
    public List<Comment> getAllComment();

    Comment getCommentById(int id);

    void saveComment(Comment comment);

    void deleteComment(Comment comment);

    void updateComment(Comment comment);
}
