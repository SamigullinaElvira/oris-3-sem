package org.example.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.models.Like;
import org.example.models.User;
import org.example.services.LikeService;

import java.io.IOException;

@WebServlet(name = "LikeServlet", value = "/like")
public class LikeServlet extends HttpServlet {

    private final LikeService likeService = new LikeService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long postId = Long.parseLong(request.getParameter("postId"));
        User user = (User) request.getSession(false).getAttribute("user");

        Like like = Like.builder().postId(postId).userId(user.getId()).build();
        likeService.pushLike(like);

        response.sendRedirect("/posts");
    }
}
