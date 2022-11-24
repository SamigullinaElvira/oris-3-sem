package org.example.services;

import org.example.models.Recension;
import org.example.repositories.RecensionRepository;

import java.util.List;
import java.util.Optional;

public class RecensionService {
    private final RecensionRepository repository = new RecensionRepository();

    public void saveRecension(Recension recension) {
        repository.save(recension);
    }

    public List<Recension> getAllRecension() {
        return repository.findAll();
    }

    public Recension getRecension(Long id) {
        Optional<Recension> recension = repository.findByID(id);
        if (recension.isPresent()) {
            return recension.get();
        } else {
            throw new IllegalArgumentException("No such recension");
        }
    }

    public void deleteRecension(Long id) {
        repository.delete(id);
    }
}
