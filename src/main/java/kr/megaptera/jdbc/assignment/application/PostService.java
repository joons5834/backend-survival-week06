package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostDao postDao;

    public PostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public List<PostDto> getAllPosts() {
        return postDao.findAll().stream()
                .map(post -> new PostDto(post))
                .toList();
    }

    public PostDto getPost(String id) {
        Post post = postDao.find(PostId.of(id));
        if (post == null) {
            return null;
        }
        return new PostDto(post);
    }

    public boolean createPost(PostDto postDto) {
        postDto.setId(PostId.generate().toString());
        Post postToCreate = new Post(postDto);
        return postDao.create(postToCreate);

    }

    public boolean updatePost(String id, PostDto postDto) {
        postDto.setId(id);
        Post postToUpdate = new Post(postDto);
        return postDao.update(postToUpdate);
    }

    public boolean deletePost(String id) {
        return postDao.delete(PostId.of(id));
    }
}
