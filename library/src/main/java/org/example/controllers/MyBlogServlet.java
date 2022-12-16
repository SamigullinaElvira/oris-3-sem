package org.example.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.models.Post;
import org.example.models.User;
import org.example.services.PostService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "MyBlogServlet", value = "/blog")
public class MyBlogServlet extends HttpServlet {

    private final PostService postService = new PostService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        Long userId = user.getId();

        List<Post> posts = postService.getAllPosts().stream()
                .filter(post -> post.getUserID().equals(userId))
                .collect(Collectors.toList());

        request.setAttribute("posts", posts);
        request.getRequestDispatcher("/WEB-INF/jsp/Posts.jsp").forward(request, response);
    }
}
