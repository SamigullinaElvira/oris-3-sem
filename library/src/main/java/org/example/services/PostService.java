package org.example.services;

import org.example.models.Post;
import org.example.repositories.PostsRepository;

import java.util.List;
import java.util.Optional;

public class PostService {
    private static final PostsRepository repository = new PostsRepository();
    public List<Post> getAllPosts() {
        return repository.findAll();
    }
    public void savePost(Post post) {
        repository.save(post);
    }
    public Post findPost(Long id) {
        Optional<Post> post = repository.findByID(id);

        if (post.isPresent()) {
            return post.get();
        } else {
            throw new IllegalArgumentException("No such post");
        }
    }
    public void updatePost(Post post) {
        repository.update(post);
    }

    public void deletePost(Long id) {
        repository.delete(id);
    }
}
