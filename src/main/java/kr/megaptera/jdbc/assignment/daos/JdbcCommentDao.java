package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcCommentDao implements CommentDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcCommentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Comment> findAll(PostId postId) {
        String sql = "SELECT id, author, content " +
                "FROM comments WHERE postId = ?;";
        List<Comment> allComments = new ArrayList<>();
        jdbcTemplate.query(sql, resultSet -> {
            CommentId id = CommentId.of(resultSet.getString("id"));
            String author = resultSet.getString("author");
            String content = resultSet.getString("content");

            Comment comment = new Comment(id, author, content);
            allComments.add(comment);
        }, postId.toString());
        return allComments;
    }

    @Override
    public boolean create(Comment comment, PostId postId) {
        String sql = "INSERT INTO comments(id, postId, author, content) " +
                "VALUES (?, ?, ?, ?);";

        int numCreated = jdbcTemplate.update(sql,
                comment.id().toString(), postId.toString(),
                comment.author(), comment.content());

        return numCreated != 0;
    }

    @Override
    public boolean update(Comment comment, PostId postId) {
        String sql = "UPDATE comments SET content = ? " +
                "WHERE id = ? AND postid = ?;";

        int numUpdated = jdbcTemplate.update(sql,
                comment.content(), comment.id().toString(),
                postId.toString());

        return numUpdated != 0;
    }

    @Override
    public boolean delete(CommentId id, PostId postId) {
        String sql = "DELETE FROM comments " +
                "WHERE id = ? AND postid = ?;";

        int numDeleted = jdbcTemplate.update(sql, id.toString(), postId.toString());

        return numDeleted != 0;
    }
}
