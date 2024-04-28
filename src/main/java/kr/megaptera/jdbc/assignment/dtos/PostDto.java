package kr.megaptera.jdbc.assignment.dtos;

import kr.megaptera.jdbc.assignment.models.Post;

public class PostDto {
    private String id;
    private String title;
    private String author;
    private String content;

    public PostDto() {
    }

    public PostDto(Post post) {
        this.id = post.id().toString();
        this.title = post.title();
        this.author = post.author();
        this.content = post.content();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
