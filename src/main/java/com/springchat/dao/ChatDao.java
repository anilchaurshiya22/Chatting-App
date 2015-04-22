package com.springchat.dao;

import com.springchat.domain.Chat;
import com.springchat.domain.ChatMessage;
import com.springchat.domain.FriendRequest;
import com.springchat.domain.User;
import java.util.Date;
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
    
    public int updateChatMemberLastActivity(int chatId, long userId) {
        
        Query query = sf.getCurrentSession().createSQLQuery("update chatmember m  set m.lastActivity = :lastDate where m.chat_id = :chatId and m.user_id = :userId");
        query.setParameter("chatId", chatId);
        query.setParameter("userId", userId);
        query.setParameter("lastDate", new Date());
        return query.executeUpdate();
    }
    
    public Chat getChatById(int chatId, long userId) {
        Query query = sf.getCurrentSession().createQuery("select c from Chat c join c.members m where c.id = :chatId and m.user.id = :userId");
        query.setParameter("chatId", chatId);
        query.setParameter("userId", userId);
        return (Chat) query.uniqueResult();
    }
    
    public List<Chat> getChatListByUserId(long userId) {
        Query query = sf.getCurrentSession().createQuery("select c from Chat c join c.members m where m.user.id = :userId");
        query.setParameter("userId", userId);
        return query.list();
    }
    
    public List<ChatMessage> getNewMessages(int chatId, long userId, int messageId) {
        Query query = sf.getCurrentSession().createQuery("select m from Chat c join c.messages m join c.members u where c.id = :chatId and u.user.id = :userId and m.id > :messageId");
        query.setParameter("chatId", chatId);
        query.setParameter("userId", userId);
        query.setParameter("messageId", messageId);
        return query.list();
    }
}
