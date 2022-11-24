package org.example.services;

import org.example.models.User;
import org.example.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {
    private static final UserRepository repository = new UserRepository();

    public void saveUser(User user) {
        repository.save(user);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUser(Long id) {
        Optional<User> user = repository.findByID(id);
        if (user.isPresent()) {
            return user.get();
        }

        throw new IllegalArgumentException("No such user");
    }
}
