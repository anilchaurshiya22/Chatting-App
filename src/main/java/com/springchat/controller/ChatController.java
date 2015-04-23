package com.springchat.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.springchat.domain.Chat;
import com.springchat.domain.ChatMessage;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springchat.domain.User;
import com.springchat.service.ChatService;
import com.springchat.util.SecurityUtil;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    public @ResponseBody String newChat(HttpServletRequest request) throws JSONException {
        JSONObject jsonObj = new JSONObject();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        
        String[] friend_ids = request.getParameterValues("friends[]");
        String message = request.getParameter("message");
        User currentUser = securityUtil.getSessionUser();
        
        int chatId = chatService.createNewChat(currentUser, friend_ids, message);
        Chat chat = chatService.getChat(currentUser, chatId);
        
        jsonObj.put("response", "success");
        jsonObj.put("chat_id", chatId);
        jsonObj.put("chat_name", chat.getName());
        
        List<ChatMessage> messages = chat.getMessages();
	String jsonMessages = gson.toJson(messages);
        jsonObj.put("messages",jsonMessages);
        
        DateFormat df = new SimpleDateFormat("MMM d h:mm a");
        jsonObj.put("date", df.format(new Date()));
        jsonObj.put("from", currentUser.getName());
        return jsonObj.toString();
    }
    
    @RequestMapping(value="/send-message", method = RequestMethod.POST)
    public @ResponseBody String newMessage(HttpServletRequest request) throws JSONException {
        JSONObject jsonObj = new JSONObject();
        
        int chatId = Integer.parseInt(request.getParameter("chat_id"));
        String message = request.getParameter("message");
        User currentUser = securityUtil.getSessionUser();
        
        int messageId = chatService.createNewMessage(currentUser, chatId, message);
        
        jsonObj.put("response", "success");
        jsonObj.put("from", currentUser.getName());
        DateFormat df = new SimpleDateFormat("MMM d h:mm a");
        jsonObj.put("date", df.format(new Date()));
        jsonObj.put("message_id", messageId);
        return jsonObj.toString();
    }
    
    @RequestMapping(value="/get-chat/{chatId}", method = RequestMethod.GET)
    public @ResponseBody String getChat(@PathVariable int chatId) throws JSONException {
        JSONObject jsonObj = new JSONObject();
        Gson gson = new GsonBuilder().setDateFormat("MMM d h:mm a").excludeFieldsWithoutExposeAnnotation().create();
        
        User currentUser = securityUtil.getSessionUser();
        
        Chat chat = chatService.getChat(currentUser, chatId);
        List<ChatMessage> messages = chat.getMessages();
        
	String jsonChat = gson.toJson(chat);
	String jsonMessages = gson.toJson(messages);
        jsonObj.put("response", "success");
        jsonObj.put("chat",jsonChat);
        jsonObj.put("messages",jsonMessages);
        return jsonObj.toString();
    }   
    
    @RequestMapping(value="/check-new-messages/{chatId}/{messageId}/{lastChatId}", method = RequestMethod.GET)
    public @ResponseBody String getNewMessages(@PathVariable int chatId, @PathVariable int messageId, @PathVariable int lastChatId) throws JSONException {
        JSONObject jsonObj = new JSONObject();
        Gson gson = new GsonBuilder().setDateFormat("MMM d h:mm a").excludeFieldsWithoutExposeAnnotation().create();
        
        User currentUser = securityUtil.getSessionUser();
        
        List<Chat> chats = chatService.getAllChats(currentUser.getId());
        List<ChatMessage> messages = chatService.getNewChatMessages(currentUser, chatId, messageId);
        List<Chat> updatedChats = chatService.getAllUpdatedChats(currentUser.getId());
        if(((Chat) chats.get(0)).getId() == lastChatId) {
            jsonObj.put("chats","noChat");
        } else {
            String jsonChats = gson.toJson(chats);
            jsonObj.put("chats",jsonChats);
        }
        
        int[] updatedChatIds = new int[updatedChats.size()];
        int i=0;
        for (Chat updatedChat : updatedChats) {
            updatedChatIds[i++] = updatedChat.getId();
        }
        String jsonMessages = gson.toJson(messages);
        String jsonUpdatedChatIds = gson.toJson(updatedChatIds);
        jsonObj.put("response", "success");
        jsonObj.put("messages",jsonMessages);
        jsonObj.put("updatedChatIds",jsonUpdatedChatIds);
        return jsonObj.toString();
    }  
    
    @RequestMapping(value="/check-new-alert/{lastChatId}", method = RequestMethod.GET)
    public @ResponseBody String getNewMessages(@PathVariable int lastChatId) throws JSONException {
        JSONObject jsonObj = new JSONObject();
        Gson gson = new GsonBuilder().setDateFormat("MMM d h:mm a").excludeFieldsWithoutExposeAnnotation().create();
        
        User currentUser = securityUtil.getSessionUser();
        
        List<Chat> chats = chatService.getAllChats(currentUser.getId());
        List<Chat> updatedChats = chatService.getAllUpdatedChats(currentUser.getId());
        if(((Chat) chats.get(0)).getId() == lastChatId) {
            jsonObj.put("chats","noChat");
        } else {
            String jsonChats = gson.toJson(chats);
            jsonObj.put("chats",jsonChats);
        }
        
        int[] updatedChatIds = new int[updatedChats.size()];
        int i=0;
        for (Chat updatedChat : updatedChats) {
            updatedChatIds[i++] = updatedChat.getId();
        }
        String jsonUpdatedChatIds = gson.toJson(updatedChatIds);
        jsonObj.put("response", "success");
        jsonObj.put("updatedChatIds",jsonUpdatedChatIds);
        return jsonObj.toString();
    }  
}
