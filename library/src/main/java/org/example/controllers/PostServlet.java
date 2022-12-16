package org.example.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.extern.java.Log;
import org.example.models.Post;
import org.example.models.Recension;
import org.example.services.PostService;
import org.example.services.RecensionService;
import org.example.utils.UserUtils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "PostServlet", urlPatterns = "/posts/*")
public class PostServlet extends HttpServlet {

    private final PostService postService = new PostService();
    private final RecensionService recensionService = new RecensionService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long postID = Long.parseLong(request.getPathInfo().substring(1));
            Post post = postService.findPost(postID);

            List<Recension> allRecension = recensionService.getAllRecension().stream()
                    .filter(recension -> recension.getPostID().equals(postID)).collect(Collectors.toList());

            request.setAttribute("recension", allRecension);
            request.setAttribute("post", post);
            request.getRequestDispatcher("/WEB-INF/jsp/Post.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect("/posts");
            return;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("recension");
        Long postID = Long.parseLong(request.getPathInfo().substring(1));
        Long authUserID = UserUtils.getAuthUserID(request);

        Recension recension = Recension.builder().text(text).userID(authUserID).postID(postID).build();
        recensionService.saveRecension(recension);

        response.sendRedirect("/posts/" + postID);
    }
}
