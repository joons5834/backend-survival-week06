package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentDao commentDao;

    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public List<CommentDto> getAllComments(String postId) {
        return commentDao.findAll(PostId.of(postId))
                .stream().map(CommentDto::new)
                .toList();
    }

    public boolean createAComment(String postId, CommentDto commentDto) {
        commentDto.setId(CommentId.generate());
        return commentDao.create(new Comment(commentDto), PostId.of(postId));
    }

    public boolean updateAComment(String id, String postId, CommentDto commentDto) {
        commentDto.setId(id);
        return commentDao.update(new Comment(commentDto), PostId.of(postId));
    }

    public boolean deleteAComment(String id, String postId) {
        return commentDao.delete(CommentId.of(id),
                PostId.of(postId));
    }
}
