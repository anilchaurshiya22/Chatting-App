package com.springchat.dao;

import com.springchat.domain.Chat;
import com.springchat.domain.ChatMessage;
import com.springchat.domain.FriendRequest;
import com.springchat.domain.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Devil
 */
@Transactional
@Repository
public class ChatDao {

    private SessionFactory sf;

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    public void insertChat(Chat chat) {
        sf.getCurrentSession().persist(chat);
    }
            
    public void insertChatMessage(ChatMessage chatMessage) {
        sf.getCurrentSession().persist(chatMessage);
    }
    
    public Chat getChatById(int chatId) {
        return (Chat) sf.getCurrentSession().load(Chat.class, chatId);
    }
    
    public List<Chat> getChatListByUserId(long userId) {
        Query query = sf.getCurrentSession().createQuery("select c from Chat c join c.members m where m.user.id = :userId");
        query.setParameter("userId", userId);
        return query.list();
    }
}
