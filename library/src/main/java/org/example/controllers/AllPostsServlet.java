package org.example.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.dto.PostDTO;
import org.example.models.Post;
import org.example.models.User;
import org.example.services.LikeService;
import org.example.services.PostService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "AllPostsServlet", value = "/posts")
public class AllPostsServlet extends HttpServlet {

    private final PostService postService = new PostService();
    private final LikeService likeService = new LikeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Post> posts = postService.getAllPosts();
        HttpSession session = request.getSession(false);
        boolean isAuth = session != null && session.getAttribute("user") != null;

        List<PostDTO> postDTOS = posts.stream()
                    .map(post -> {
                        if (isAuth) {
                            User user = (User) session.getAttribute("user");
                            return PostDTO.builder()
                                    .id(post.getId())
                                    .img(post.getImg())
                                    .imgName(post.getImgName())
                                    .title(post.getTitle())
                                    .text(post.getText())
                                    .userID(post.getUserID())
                                    .isLiked(likeService.isPostLikedByUser(user.getId(), post.getId()))
                                    .build();

                        }

                        return PostDTO.builder()
                                .id(post.getId())
                                .img(post.getImg())
                                .imgName(post.getImgName())
                                .title(post.getTitle())
                                .text(post.getText())
                                .userID(post.getUserID())
                                .isLiked(false)
                                .build();

                    }).collect(Collectors.toList());

        request.setAttribute("posts", postDTOS);
        request.setAttribute("showLike", true);
        request.getRequestDispatcher("/WEB-INF/jsp/Posts.jsp").forward(request, response);
    }

}
