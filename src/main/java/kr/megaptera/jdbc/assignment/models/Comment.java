package kr.megaptera.jdbc.assignment.models;

import kr.megaptera.jdbc.assignment.dtos.CommentDto;

public class Comment {
    CommentId id;
    String author;
    String content;

    public Comment(CommentId id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public Comment(CommentDto commentDto) {
        this.id = CommentId.of(commentDto.getId());
        this.author = commentDto.getAuthor();
        this.content = commentDto.getContent();
    }

    public CommentId id() {
        return id;
    }

    public String author() {
        return author;
    }

    public String content() {
        return content;
    }
}
