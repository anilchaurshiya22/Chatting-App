package com.springchat.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springchat.domain.Chat;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author 984171
 */
@Controller
@RequestMapping("/chat")
public class ChatController {

    @RequestMapping("")
    public String index() {
        return "chat/index";
    }
    
    @RequestMapping(value="/new-chat", method = RequestMethod.POST)
    public @ResponseBody String newChat(Model model, HttpServletRequest request) throws JSONException {
        JSONObject jsonObj = new JSONObject();
        String[] friend_ids = request.getParameterValues("friends[]");
        String message = request.getParameter("message");
        Chat chat = new Chat();
        if(friend_ids.length > 1) {
            chat.setIsGroup(true);
        } else {
            chat.setIsGroup(false);
        }
        jsonObj.put("message", message);
        jsonObj.put("chat", chat);
        return jsonObj.toString();
//        return "{\"id\":0,\"name\":null,\"lastUpdated\":null,\"isGroup\":null}";
    }
    
}
