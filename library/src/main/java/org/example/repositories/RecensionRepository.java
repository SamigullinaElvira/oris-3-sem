package org.example.repositories;

import org.example.config.PostgresConnectionProvider;
import org.example.models.Recension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class RecensionRepository {
    public void save(Recension recension) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into recension_table (text, user_id, post_id) VALUES (?, ?, ?)"
            );

            statement.setString(1, recension.getText());
            statement.setLong(2, recension.getUserID());
            statement.setLong(3, recension.getPostID());

            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<Recension> findAll() {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from recension_table"
            );

            ResultSet resultSet = statement.executeQuery();
            List<Recension> result = new LinkedList<>();

            while (resultSet.next()) {
                result.add(
                        Recension.builder()
                                .id(resultSet.getLong("id"))
                                .text(resultSet.getString("text"))
                                .userID(resultSet.getLong("user_id"))
                                .postID(resultSet.getLong("post_id"))
                                .build()
                );
            }

            return result;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Optional<Recension> findByID(Long id) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from recension_table where id = ?"
            );

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(
                        Recension.builder()
                                .id(resultSet.getLong("id"))
                                .text(resultSet.getString("text"))
                                .userID(resultSet.getLong("user_id"))
                                .postID(resultSet.getLong("post_id"))
                                .build()
                );
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void delete(Long id) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "delete from recension_table where id = ?"
            );

            statement.setLong(1, id);

            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
