package com.springchat.util;

import com.springchat.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static User getSessionUser() {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        return user;
    }

}
