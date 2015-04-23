package com.springchat.service;

import com.springchat.domain.Chat;
import com.springchat.domain.ChatMessage;
import com.springchat.domain.User;
import java.util.List;

/**
 *
 * @author Devil
 */
public interface ChatService {

    public int createNewChat(User currentUser, String[] friend_ids, String message);
    
    public int createNewMessage(User currentUser, int chatId, String message);
    
    public List<Chat> getAllChats(long userId);
            
    public List<Chat> getAllUpdatedChats(long userId);
    
    public Chat getChat(User currentUser, int chatId);
    
    public List<ChatMessage> getNewChatMessages(User currentUser, int chatId, int messageId);
}
