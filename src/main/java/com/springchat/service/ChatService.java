package com.springchat.service;

import com.springchat.domain.Chat;
import com.springchat.domain.User;
import java.util.List;

/**
 *
 * @author Devil
 */
public interface ChatService {

    public Chat createNewChat(User currentUser, String[] friend_ids, String message);
    
    public int createNewMessage(User currentUser, int chatId, String message);
    
    public List<Chat> getAllChats(long userId);
}
