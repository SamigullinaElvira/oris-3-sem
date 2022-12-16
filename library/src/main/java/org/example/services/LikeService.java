package org.example.services;

import org.example.models.Like;
import org.example.repositories.LikeRepository;

import java.util.List;

public class LikeService {
    private final LikeRepository likeRepository = new LikeRepository();
    public void pushLike(Like like) {
        Long likeId = null;
        Long postId = like.getPostId();
        Long userId = like.getUserId();

        for (Like l : likeRepository.findAll()) {
            if (l.getPostId().equals(postId) && l.getUserId().equals(userId)) {
                likeId = l.getId();
                break;
            }
        }

        if (likeId == null) {
            likeRepository.save(like);
        } else {
            likeRepository.delete(likeId);
        }

    }

    public boolean isPostLikedByUser(Long userId, Long postId) {
        List<Like> likes = likeRepository.findAll();

        for (Like like : likes) {
            if (like.getUserId().equals(userId) && like.getPostId().equals(postId)) {
                return true;
            }
        }

        return false;
    }

    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }
}
