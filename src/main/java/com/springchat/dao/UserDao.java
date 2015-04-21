package com.springchat.dao;

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
 
    public void insertNewUser(User user) {
        sf.getCurrentSession().persist(user); 
    }

    public void updateUser(User user) {
      sf.getCurrentSession().update(user);
    }

    public User checkEmail(String email) {
        Query query = sf.getCurrentSession().createQuery("from User u where u.email=:email");
        query.setParameter("email", email);
        return (User) query.uniqueResult();
    }
}
