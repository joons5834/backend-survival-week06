package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;

import java.util.List;

public interface PostDao {
    List<Post> findAll();

    Post find(PostId postId);

    boolean create(Post post);

    boolean update(Post post);

    boolean delete(PostId id);
}
