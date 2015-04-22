package com.springchat.controller;

import com.springchat.domain.Chat;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springchat.domain.User;
import com.springchat.service.ChatService;
import com.springchat.util.SecurityUtil;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author 984171
 */
@Controller
@RequestMapping("/chat")
public class ChatController {
    
    @Autowired
    private SecurityUtil securityUtil;
    
    @Autowired
    private ChatService chatService;

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("name", securityUtil.getSessionUser().getName());
        model.addAttribute("chats", chatService.getAllChats(securityUtil.getSessionUser().getId()));
        model.addAttribute("friends", securityUtil.getSessionUser().getFriends());
        return "chat/index";
    }
    
    @RequestMapping(value="/new-chat", method = RequestMethod.POST)
    public @ResponseBody String newChat(Model model, HttpServletRequest request) throws JSONException {
        JSONObject jsonObj = new JSONObject();
        
        String[] friend_ids = request.getParameterValues("friends[]");
        String message = request.getParameter("message");
        User currentUser = securityUtil.getSessionUser();
        
        Chat chat = chatService.createNewChat(currentUser, friend_ids, message);
        
        jsonObj.put("response", "success");
        jsonObj.put("chat_id", chat.getId());
        jsonObj.put("chat_name", chat.getName());
        jsonObj.put("from", currentUser.getName());
        return jsonObj.toString();
    }
    
    @RequestMapping(value="/send-message", method = RequestMethod.POST)
    public @ResponseBody String newMessage(Model model, HttpServletRequest request) throws JSONException {
        JSONObject jsonObj = new JSONObject();
        
        int chat_id = Integer.parseInt(request.getParameter("chat_id"));
        String message = request.getParameter("message");
        User currentUser = securityUtil.getSessionUser();
        
        int messageId = chatService.createNewMessage(currentUser, chat_id, message);
        
        jsonObj.put("response", "success");
        jsonObj.put("from", currentUser.getName());
        jsonObj.put("message_id", messageId);
        return jsonObj.toString();
    }
    
}
