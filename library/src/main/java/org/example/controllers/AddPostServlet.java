package org.example.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.models.Post;
import org.example.services.PostService;
import org.example.utils.UserUtils;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "AddPostServlet", value = "/posts/add")
@MultipartConfig
public class AddPostServlet extends HttpServlet {

    private final PostService postService = new PostService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/AddPost.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String title = request.getParameter("title");
        String text = request.getParameter("text");
        Long userID = UserUtils.getAuthUserID(request);

        Part part = request.getPart("img");
        String originalName = part.getSubmittedFileName();
        String storageName = UUID.randomUUID() + "_" + originalName;

        Post post = Post.builder()
                .title(title)
                .text(text)
                .userID(userID)
                .imgName(storageName)
                .img(part.getInputStream().readAllBytes())
                .build();

        postService.savePost(post);

        response.sendRedirect("/posts");
    }
}
