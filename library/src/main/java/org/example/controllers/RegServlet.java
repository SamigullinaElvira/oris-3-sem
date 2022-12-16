package org.example.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.models.User;
import org.example.services.UserService;
import org.example.utils.MD5HF;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RegServlet", value = "/reg")
public class RegServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/Reg.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        password = MD5HF.hashPassword(password);

        List<User> users = userService.getAllUsers();

        for (User user : users) {
            if (user.getLogin().equals(login)) {
                response.sendRedirect("/reg");
                return;
            }
        }

        User user = User.builder().login(login).password(password).build();
        userService.saveUser(user);

        response.sendRedirect("/auth");
    }
}
