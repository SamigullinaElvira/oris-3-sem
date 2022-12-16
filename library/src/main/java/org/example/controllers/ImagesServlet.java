package org.example.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.models.Post;
import org.example.services.PostService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ImagesServlet", value = "/images/*")
public class ImagesServlet extends HttpServlet {
    private final PostService postService = new PostService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String storageName = request.getPathInfo().substring(1);
        List<Post> posts = postService.getAllPosts();

        for (Post post : posts) {
                if (post.getImg() != null && post.getImgName().equals(storageName)) {
                byte[] data = post.getImg();
                response.setContentType(getServletContext().getMimeType(storageName));
                response.setContentLength(data.length);
                response.getOutputStream().write(data);
                return;
            }
        }
    }

}
