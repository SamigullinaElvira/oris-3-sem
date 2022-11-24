package org.example.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.models.Post;
import org.example.services.PostService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AllPostsServlet", value = "/posts")
public class AllPostsServlet extends HttpServlet {

    private final PostService postService = new PostService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Post> posts = postService.getAllPosts();
        request.setAttribute("posts", posts);
        request.getRequestDispatcher("/WEB-INF/jsp/Posts.jsp").forward(request, response);
    }

}
