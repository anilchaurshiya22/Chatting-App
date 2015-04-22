package com.springchat.serviceImpl;

import com.springchat.dao.ChatDao;
import com.springchat.dao.UserDao;
import com.springchat.domain.Chat;
import com.springchat.domain.ChatMember;
import com.springchat.domain.ChatMessage;
import com.springchat.domain.User;
import com.springchat.service.ChatService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Devil
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
@Service
public class ChatServiceImpl implements ChatService {

    private ChatDao chatDao;
    private UserDao userDao;

    public void setChatDao(ChatDao chatDao) {
        this.chatDao = chatDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Chat createNewChat(User currentUser, String[] friend_ids, String message) {
        Chat chat = new Chat();
        
        List<ChatMember> members = new ArrayList<>();
        String chatName = currentUser.getName();
        ChatMember chatMember = new ChatMember(currentUser);
        members.add(chatMember);
        
        if(friend_ids.length > 1) {
            for(String friend_id: friend_ids) {
                chatMember = new ChatMember(userDao.findUserById(Long.parseLong(friend_id)));
                chatName += ", " + chatMember.getUser().getName();
                members.add(chatMember);
            }
            chat.setName(chatName);
            chat.setIsGroup(true);
            chat.setChatStatus(currentUser.getName() + " started group chat");
        } else {
            chatMember = new ChatMember(userDao.findUserById(Long.parseLong(friend_ids[0])));
            chatName += "/" + chatMember.getUser().getName();
            members.add(chatMember);
            chat.setName(chatName);
            chat.setIsGroup(false);
            chat.setChatStatus(currentUser.getName() + " started chat");
        }
        
        chat.setMembers(members);
        
        chatDao.insertChat(chat);
        
        ChatMessage chatmessage = new ChatMessage(currentUser, message, chat);
        chatDao.insertChatMessage(chatmessage);
        
        return chat;
    }

    @Override
    public int createNewMessage(User currentUser, int chatId, String message) {
        Chat chat = chatDao.getChatById(chatId);
        ChatMessage chatmessage = new ChatMessage(currentUser, message, chat);
        chatDao.insertChatMessage(chatmessage);
        return chatmessage.getId();
    }

    @Override
    public List<Chat> getAllChats(long userId) {
        return chatDao.getChatListByUserId(userId);
    }
        
}
