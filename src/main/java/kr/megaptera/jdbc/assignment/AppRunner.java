package kr.megaptera.jdbc.assignment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;

    public AppRunner(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) {
        // TODO: posts 와 comments 테이블을 생성해 주세요.
        String createPostsTable = """
                CREATE TABLE IF NOT EXISTS posts(
                    id varchar(255) PRIMARY KEY,
                    title varchar(255),
                    author varchar(255),
                    content varchar(255)
                );
                """;

        String createCommentsTable = """
                CREATE TABLE IF NOT EXISTS comments(
                    id varchar(255) PRIMARY KEY,
                    postId varchar(255),
                    author varchar(255),
                    content varchar(255)
                );
                """;

        jdbcTemplate.execute(createPostsTable);
        jdbcTemplate.execute(createCommentsTable);

        System.out.println("Created posts and comments table");
    }
}
