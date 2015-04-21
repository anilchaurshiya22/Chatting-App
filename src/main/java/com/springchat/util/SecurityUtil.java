package com.springchat.util;

import com.springchat.domain.User;
import com.springchat.service.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static User getSessionUser(UserService userService) {
            Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            User user = userService.findUserByUsername(currentUserName);
//            System.out.println("currentUserName=====" + user.getUsername());
            return user;
        }
        return null;
    }

}
