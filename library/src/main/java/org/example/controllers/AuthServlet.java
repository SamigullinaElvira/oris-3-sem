package org.example.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.models.User;
import org.example.services.UserService;
import org.example.utils.MD5HF;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AuthServlet", value = "/auth")
public class AuthServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/Auth.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        password = MD5HF.hashPassword(password);

        List<User> users = userService.getAllUsers();

        for (User user : users) {
            if (user.getLogin().equals(login)) {
                if (user.getPassword().equals(password)) {
                    request.getSession(true).setAttribute("user", user);
                    response.sendRedirect("/home");
                    return;
                }
            }
        }

        response.sendRedirect("/auth?error");
    }
}
