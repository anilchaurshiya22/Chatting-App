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
    public int createNewChat(User currentUser, String[] friend_ids, String message) {
        if (friend_ids.length == 1) {
            Chat chat = chatDao.getChatByTwoUser(currentUser.getId(), Long.parseLong(friend_ids[0]));
            if(chat != null) {
                createNewMessage(currentUser, chat.getId(), message);
                return chat.getId();
            }
        }
        Chat chat = new Chat();

        List<ChatMember> members = new ArrayList<>();
        String chatName = currentUser.getName();
        ChatMember chatMember = new ChatMember(currentUser);
        members.add(chatMember);

        if (friend_ids.length > 1) {
            for (String friend_id : friend_ids) {
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

        ChatMessage chatMessage = new ChatMessage(currentUser, message);
        chat.getMessages().add(chatMessage);
        chatMessage.setChat(chat);
        
        chatDao.insertChat(chat);
        
        return chat.getId();
    }

    @Override
    public int createNewMessage(User currentUser, int chatId, String message) {
        Chat chat = chatDao.getChatById(chatId, currentUser.getId());
        ChatMessage chatmessage = new ChatMessage(currentUser, message, chat);
        chatDao.insertChatMessage(chatmessage);
        chatDao.updateChatLastActivity(chatId);
        chatDao.updateChatMemberLastActivity(chatId, currentUser.getId());
        return chatmessage.getId();
    }

    @Override
    public List<Chat> getAllChats(long userId) {
        return chatDao.getChatListByUserId(userId);
    }

    @Override
    public List<Chat> getAllUpdatedChats(long userId) {
        return chatDao.getUpdatedChatListByUserId(userId);
    }

    @Override
    public Chat getChat(User currentUser, int chatId) {
        return chatDao.getChatById(chatId, currentUser.getId());
    }

    @Override
    public List<ChatMessage> getNewChatMessages(User currentUser, int chatId, int messageId) {
        chatDao.updateChatMemberLastActivity(chatId, currentUser.getId());
        return chatDao.getNewMessages(chatId, currentUser.getId(), messageId);
    }

}
