package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;

import java.util.List;

public interface CommentDao {
    List<Comment> findAll(PostId id);


    boolean create(Comment comment, PostId postId);

    boolean update(Comment comment, PostId of);


    boolean delete(CommentId id, PostId postId);
}
