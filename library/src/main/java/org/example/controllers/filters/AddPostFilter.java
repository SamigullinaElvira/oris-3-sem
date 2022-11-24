package org.example.controllers.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.utils.UserUtils;

import java.io.IOException;

@WebFilter(urlPatterns = "/posts/add")
public class    AddPostFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!UserUtils.isAuth()) {
            response.sendRedirect("/auth");
            return;
        }

        chain.doFilter(request, response);
    }
}
