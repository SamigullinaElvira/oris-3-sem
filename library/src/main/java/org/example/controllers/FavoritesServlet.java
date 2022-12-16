package org.example.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.models.Like;
import org.example.models.Post;
import org.example.models.User;
import org.example.services.LikeService;
import org.example.services.PostService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "FavoritesServlet", value = "/favorites")
public class FavoritesServlet extends HttpServlet {

    private final LikeService likeService = new LikeService();
    private final PostService postService = new PostService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        List<Post> posts = likeService.getAllLikes().stream()
                .filter(like -> like.getUserId().equals(user.getId()))
                .map(like -> postService.findPost(like.getPostId()))
                .collect(Collectors.toList());

        request.setAttribute("posts", posts);
        request.getRequestDispatcher("/WEB-INF/jsp/Posts.jsp").forward(request, response);
    }
}
