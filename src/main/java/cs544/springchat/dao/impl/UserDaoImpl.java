/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs544.springchat.dao.impl;
 
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
 
import cs544.springchat.dao.UserDao;
import cs544.springchat.domain.User;
/**
 *
 * @author suraj
 */
 
@Repository
public class UserDaoImpl implements UserDao {
 
	private SessionFactory sessionFactory;
 
	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {
 
		List<User> users = new ArrayList<User>();
 
		users = sessionFactory.getCurrentSession()
			.createQuery("from User where username=?")
			.setParameter(0, username)
			.list();
 
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
 
	}

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
 
}