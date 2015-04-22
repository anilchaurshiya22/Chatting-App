package com.springchat.util;

import com.springchat.domain.User;
import com.springchat.service.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

    @Autowired
    private UserService userService;

    public User getSessionUser() {
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
