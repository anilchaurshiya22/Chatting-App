package com.springchat.dao;

import com.springchat.domain.FriendRequest;
import com.springchat.domain.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Devil
 */
@Transactional
@Repository
public class UserDao {

//    @Autowired
    private SessionFactory sf;

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

//    public User findUserById(long id) {
//        
//    }
//
//    
//    public List<User> getAllUsers() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    public User findUserByUsername(String username) {
        Query query = sf.getCurrentSession().createQuery("from User u where u.username=:username");
        query.setParameter("username", username);
        return (User) query.uniqueResult();
    }

    public void addFriendRequest(FriendRequest request) {
        sf.getCurrentSession().persist(request);
    }

    public FriendRequest findFriendRequestByReceiverAndSender(User receiver, User sender) {
        Query query = sf.getCurrentSession().createQuery("from FriendRequest f where f.sender.id=:senderId and f.receiver.id=:receiverId and f.status='N'");
        query.setParameter("senderId", sender.getId());
        query.setParameter("receiverId", receiver.getId());
//        query.setParameter("status", "N");
        return (FriendRequest) query.uniqueResult();
    }

    public FriendRequest findFriendRequestByReceiverEmailAndSender(String email, User sender) {
        Query query = sf.getCurrentSession().createQuery("from FriendRequest f where f.sender.id=:senderId and f.receiverEmail=:receiverEmail and f.status='N'");
        query.setParameter("senderId", sender.getId());
        query.setParameter("receiverEmail", email);
        return (FriendRequest) query.uniqueResult();
    }

    public List<FriendRequest> findFriendRequestByUsernameAndStatus(User sender) {
        Query query = sf.getCurrentSession().createQuery("from FriendRequest f where f.sender.id=:senderId and f.status='N'");
        query.setParameter("senderId", sender.getId());
        return query.list();
    }
}
