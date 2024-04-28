package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.PostService;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.exceptions.PostNotCreated;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostDto> list() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public PostDto detail(@PathVariable String id) {
        PostDto postDto = postService.getPost(id);
        if (postDto == null) {
            throw new PostNotFound();
        }
        return postDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody PostDto postDto) {
        boolean isPostCreated = postService.createPost(postDto);
        if (!isPostCreated) throw new PostNotCreated();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id,
                       @RequestBody PostDto postDto) {
        boolean isPostUpdated = postService.updatePost(id, postDto);
        if (!isPostUpdated) throw new PostNotFound();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        boolean isPostDeleted = postService.deletePost(id);
        if (!isPostDeleted) throw new PostNotFound();
    }

    @ExceptionHandler(PostNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound() {
        return "게시물을 찾을 수 없습니다.";
    }

    @ExceptionHandler(PostNotCreated.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String postNotCreated() {
        return "게시물이 생성되지 않았습니다.";
    }

}
