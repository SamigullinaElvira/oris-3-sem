package org.example.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.models.Post;
import org.example.services.PostService;
import org.example.utils.UserUtils;

import java.io.IOException;

@WebServlet(name = "AddPostServlet", value = "/posts/add")
public class AddPostServlet extends HttpServlet {

    private final PostService postService = new PostService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/AddPost.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String text = request.getParameter("text");
        Long userID = UserUtils.getAuthUserID(request);

        Post post = Post.builder().title(title).text(text).userID(userID).build();
        postService.savePost(post);

        response.sendRedirect("/posts");
    }
}
