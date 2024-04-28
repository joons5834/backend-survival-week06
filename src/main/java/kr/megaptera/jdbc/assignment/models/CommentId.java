package kr.megaptera.jdbc.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;

public class CommentId {
    String id;

    public CommentId(String id) {
        this.id = id;
    }

    public static CommentId of(String id) {
        return new CommentId(id);
    }

    public static String generate() {
        return TsidCreator.getTsid().toString();
    }

    @Override
    public String toString() {
        return id;
    }
}
