package org.example.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.controllers.listeners.ContextInitListener;
import org.example.models.User;
import org.example.services.RecensionService;
import org.example.services.UserService;

import java.util.Optional;

public class UserUtils {
    private static final UserService userService = new UserService();
    private static final RecensionService recensionService = new RecensionService();
    public static boolean isAuth() {
        return ContextInitListener.getAuthUser().isPresent();
    }

    public static Long getAuthUserID(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return user.getId();
    }

    public static boolean isPostBelongsToAuthUser(Long postID) {
        Optional<User> optional = ContextInitListener.getAuthUser();
        return optional.map(user -> user.getId().equals(postID)).orElse(false);
    }

    public static boolean isAdmin() {
        Optional<User> user = ContextInitListener.getAuthUser();
        return user.map(value -> value.getRole().equals("admin")).orElse(false);
    }

    public static String getRecensionAuthorName(Long authorID) {
        return userService.getUser(authorID).getLogin();
    }

    public static boolean isRecensionBelongsToUser() { return false; }
}
