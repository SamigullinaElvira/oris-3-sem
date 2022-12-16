package org.example.controllers.listeners;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.config.PostgresConnectionProvider;
import org.example.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

@WebListener
public class ContextInitListener implements ServletContextListener, HttpSessionListener {

    private static HttpSession session;

    public static Optional<User> getAuthUser() {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        // language=SQL
        String createUser = "create table if not exists user_table (" +
                "id bigserial primary key," +
                "login varchar(200)," +
                "password varchar(200)," +
                "role varchar(50) default 'user')";

        // language=SQL
        String createPost = "create table if not exists post_table (" +
                "id bigserial primary key," +
                "title varchar(500)," +
                "text varchar(2000)," +
                "user_id bigint references user_table(id))";

        // language=SQL
        String createRecension = "create table if not exists recension_table (" +
                "id bigserial primary key," +
                "text varchar(1000)," +
                "user_id bigint references user_table(id)," +
                "post_id bigint references post_table(id))";

        // language=SQL
        String createLikeTable = "create table if not exists like_table (" +
                "id bigserial primary key," +
                "post_id bigint references post_table(id)," +
                "user_id bigint references user_table(id))";

        // language=SQL
        String alterImages = "alter table post_table add column if not exists img bytea";

        // language=SQL
        String alterImageName = "alter table post_table add column if not exists img_name varchar(100)";

        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(createUser);
            statement.execute();

            statement = connection.prepareStatement(createPost);
            statement.execute();

            statement = connection.prepareStatement(createRecension);
            statement.execute();

            statement = connection.prepareStatement(alterImages);
            statement.execute();

            statement = connection.prepareStatement(alterImageName);
            statement.execute();

            statement = connection.prepareStatement(createLikeTable);
            statement.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        session = se.getSession();
    }

}
