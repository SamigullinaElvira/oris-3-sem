package org.example.repositories;

import org.example.config.PostgresConnectionProvider;
import org.example.models.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostsRepository {
    public List<Post> findAll() {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from post_table"
            );

            ResultSet set = statement.executeQuery();
            List<Post> result = new ArrayList<>();

            while (set.next()) {
                Post post = Post.builder()
                        .id(set.getLong("id"))
                        .title(set.getString("title"))
                        .text(set.getString("text"))
                        .userID(set.getLong("user_id"))
                        .img(set.getBytes("img"))
                        .imgName(set.getString("img_name"))
                        .build();

                result.add(post);
            }

            return result;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void save(Post post) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into post_table (title, text, user_id, img_name, img) values (?, ?, ?, ?, ?)"
            );

            statement.setString(1, post.getTitle());
            statement.setString(2, post.getText());
            statement.setLong(3, post.getUserID());
            statement.setString(4, post.getImgName());
            statement.setBytes(5, post.getImg());

            statement.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Optional<Post> findByID(Long id) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from post_table where id = ?"
            );

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(
                        Post.builder()
                                .id(resultSet.getLong("id"))
                                .title(resultSet.getString("title"))
                                .text(resultSet.getString("text"))
                                .userID(resultSet.getLong("user_id"))
                                .imgName(resultSet.getString("img_name"))
                                .img(resultSet.getBytes("img"))
                                .build()
                );
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void update(Post post) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "update post_table set title = ?, text = ? where id = ?"
            );

            statement.setString(1, post.getTitle());
            statement.setString(2, post.getText());
            statement.setLong(3, post.getId());

            statement.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void delete(Long id) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "delete from post_table where id = ?"
            );

            statement.setLong(1, id);

            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
