package org.example.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.models.Recension;
import org.example.services.PostService;
import org.example.services.RecensionService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeletePostServlet", value = "/posts/delete")
public class DeletePostServlet extends HttpServlet {

    private final PostService postService = new PostService();
    private final RecensionService recensionService = new RecensionService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long postID = Long.parseLong(request.getParameter("postID"));
        List<Recension> recensions = recensionService.getAllRecension();

        for (Recension recension : recensions) {
            if (recension.getPostID().equals(postID)) {
                recensionService.deleteRecension(recension.getId());
            }
        }

        postService.deletePost(postID);
        response.sendRedirect("/posts");
    }
}
