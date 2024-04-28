package kr.megaptera.jdbc.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;

public class PostId {
    String id;

    public PostId(String id) {
        this.id = id;
    }

    public static PostId of(String id) {
        return new PostId(id);
    }

    public static PostId generate() {
        return of(TsidCreator.getTsid().toString());
    }


    @Override
    public String toString() {
        return id;
    }
}
