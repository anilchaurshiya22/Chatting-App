package com.springchat.serviceImpl;

import com.springchat.dao.UserDao;
import com.springchat.domain.User;
import com.springchat.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Devil
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {

//    @Autowired
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
//    @Override
//    public User findUserById(long id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    @Override
    public User findUserByUsername(String username) {
       return userDao.findUserByUsername(username);
    }

}
