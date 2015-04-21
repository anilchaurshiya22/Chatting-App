package com.springchat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author 984171
 */
@Controller 
public class ChatController {

    @RequestMapping("/chat")
    public String index() {
        return "chat/index";
    }
    
}
