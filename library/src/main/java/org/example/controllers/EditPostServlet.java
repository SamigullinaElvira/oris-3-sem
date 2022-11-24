package org.example.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.models.Post;
import org.example.services.PostService;

import java.io.IOException;

@WebServlet(name = "EditPostServlet", value = "/posts/edit")
public class EditPostServlet extends HttpServlet {

    private final PostService postService = new PostService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long postID = Long.parseLong(request.getParameter("postID"));
        Post post = postService.findPost(postID);
        request.setAttribute("post", post);
        request.getRequestDispatcher("/WEB-INF/jsp/EditPost.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String text = request.getParameter("text");
        Post post = postService.findPost(Long.parseLong(request.getParameter("postID")));
        post.setTitle(title);
        post.setText(text);

        postService.updatePost(post);
        response.sendRedirect("/posts/" + request.getParameter("postID"));
    }
}
