package com.springchat.controller.rest;

import com.springchat.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author 984171
 */
@Controller("restChatController")
@RequestMapping("/rest")  
public class ChatController {

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody User user() {

        User user = new User();
        user.setUsername("Test");
        user.setPassword("Test");
        return user;
    }
    
}
