package kr.megaptera.jdbc.assignment.models;

import kr.megaptera.jdbc.assignment.dtos.PostDto;

public class Post {
    PostId id;
    String title;
    String author;
    String content;

    public Post(PostId postId, String title, String author, String content) {
        this.id = postId;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post(PostDto postDto) {
        this.id = PostId.of(postDto.getId());
        this.title = postDto.getTitle();
        this.author = postDto.getAuthor();
        this.content = postDto.getContent();
    }

    public PostId id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String author() {
        return author;
    }

    public String content() {
        return content;
    }
}
