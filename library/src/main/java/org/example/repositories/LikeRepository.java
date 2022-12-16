package org.example.repositories;

import org.example.config.PostgresConnectionProvider;
import org.example.models.Like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class LikeRepository {
    public void save(Like like) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into like_table (post_id, user_id) values (?, ?)"
            );

            statement.setLong(1, like.getPostId());
            statement.setLong(2, like.getUserId());
            statement.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void delete(Long id) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "delete from like_table where id = ?"
            );

            statement.setLong(1, id);
            statement.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<Like> findAll() {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from like_table"
            );

            ResultSet resultSet = statement.executeQuery();
            List<Like> list = new LinkedList<>();

            while (resultSet.next()) {
                Like like = Like.builder()
                        .id(resultSet.getLong("id"))
                        .userId(resultSet.getLong("user_id"))
                        .postId(resultSet.getLong("post_id"))
                        .build();
                list.add(like);
            }

            return list;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Optional<Like> findById(Long id) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from like_table where id = ?"
            );

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Like like = Like.builder()
                        .id(resultSet.getLong("id"))
                        .userId(resultSet.getLong("user_id"))
                        .postId(resultSet.getLong("post_id"))
                        .build();

                return Optional.of(like);
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
