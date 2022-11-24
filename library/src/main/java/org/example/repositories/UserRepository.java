package org.example.repositories;

import org.example.config.PostgresConnectionProvider;
import org.example.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    public void save(User user) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into user_table (login, password) values (?, ?)"
            );

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());

            statement.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<User> findAll() {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from user_table"
            );

            ResultSet set = statement.executeQuery();

            List<User> result = new ArrayList<>();

            while (set.next()) {
                User user = User.builder()
                        .id(set.getLong("id"))
                        .login(set.getString("login"))
                        .password(set.getString("password"))
                        .role(set.getString("role"))
                        .build();

                result.add(user);
            }

            return result;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Optional<User> findByID(Long id) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from user_table where id = ?"
            );

            statement.setLong(1, id);

            ResultSet set = statement.executeQuery();
            if (set.next()) {
                return Optional.of(
                        User.builder()
                                .id(set.getLong("id"))
                                .login(set.getString("login"))
                                .password(set.getString("password"))
                                .role(set.getString("role"))
                                .build()
                );
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
