package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.CommentService;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.exceptions.CommentNotCreated;
import kr.megaptera.jdbc.assignment.exceptions.CommentNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentDto> list(@RequestParam String postId) {
        return commentService.getAllComments(postId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestParam String postId,
                       @RequestBody CommentDto commentDto) {
        boolean isCommentCreated =
                commentService.createAComment(postId, commentDto);
        if (!isCommentCreated) {
            throw new CommentNotCreated();
        }
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id,
                       @RequestBody CommentDto commentDto,
                       @RequestParam String postId) {
        boolean isCommentUpdated =
                commentService.updateAComment(id, postId, commentDto);
        if (!isCommentUpdated) {
            throw new CommentNotFound();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id,
                       @RequestParam String postId) {
        boolean isCommentDeleted =
                commentService.deleteAComment(id, postId);
        if (!isCommentDeleted) {
            throw new CommentNotFound();
        }
    }

    @ExceptionHandler(CommentNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String commentNotFound() {
        return "댓글을 찾을 수 없습니다.";
    }

    @ExceptionHandler(CommentNotCreated.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String commentNotCreated() {
        return "댓글이 생성되지 않았습니다.";
    }
}
