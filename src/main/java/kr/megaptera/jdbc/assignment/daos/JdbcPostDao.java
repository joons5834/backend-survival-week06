package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcPostDao implements PostDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcPostDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Post> findAll() {
        String sql = "SELECT id, title, author, content from posts;";
        List<Post> allPosts = new ArrayList<>();
        jdbcTemplate.query(sql, resultSet -> {
            PostId postId = PostId.of(resultSet.getString("id"));
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            String content = resultSet.getString("content");

            Post post = new Post(postId, title, author, content);
            allPosts.add(post);
        });
        return allPosts;
    }

    @Override
    public Post find(PostId id) {
        String sql = "SELECT id, title, author, content FROM posts" +
                " WHERE id = ? ;";
        List<Post> postFound = new ArrayList<>();
        jdbcTemplate.query(sql, resultSet -> {
            PostId postId = PostId.of(resultSet.getString("id"));
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            String content = resultSet.getString("content");

            postFound.add(new Post(postId, title, author, content));
        }, id.toString());

        if (postFound.isEmpty()) {
            return null;
        } else {
            return postFound.get(0);
        }
    }

    @Override
    public boolean create(Post post) {
        String sql = "INSERT INTO posts(id, title, author, content) " +
                "VALUES (?, ?, ?, ?);";

        int numCreated = jdbcTemplate.update(sql, post.id().toString(),
                post.title(), post.author(), post.content());

        return numCreated != 0;
    }

    @Override
    public boolean update(Post post) {
        String sql = "UPDATE posts SET title = ?, content = ? " +
                "WHERE id = ?;";

        int numUpdated = jdbcTemplate.update(sql, post.title(),
                post.content(), post.id().toString());

        return numUpdated != 0;
    }

    @Override
    public boolean delete(PostId id) {
        String sql = "DELETE FROM posts WHERE id = ?;";

        int numDeleted = jdbcTemplate.update(sql, id.toString());

        return numDeleted != 0;

    }
}
