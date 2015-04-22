package com.springchat.serviceImpl;

import com.springchat.dao.UserDao;
import com.springchat.domain.FriendRequest;
import com.springchat.domain.User;
import com.springchat.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Devil
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
@Service
public class UserServiceImpl implements UserService {

//    @Autowired
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findUserById(long id) {
        return userDao.findUserById(id);
    }

//    @Override
//    public List<User> getAllUsers() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    @Override
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    public void addFriendRequest(FriendRequest request) {
        userDao.addFriendRequest(request);
    }

    @Override
    public FriendRequest findFriendRequestByReceiverAndSender(User receiver, User sender) {
        return userDao.findFriendRequestByReceiverAndSender(receiver, sender);
    }

    @Override
    public FriendRequest findFriendRequestByReceiverEmailAndSender(String email, User sender) {
        return userDao.findFriendRequestByReceiverEmailAndSender(email, sender);
    }

    @Override
    public List<FriendRequest> getAllFriendRequestByUsernameAndStatus(User sender) {
        return userDao.getAllFriendRequestByUsernameAndStatus(sender);
    }

    @Override
    public void insertNewUser(User user) {
        userDao.insertNewUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public User checkEmail(String email) {
        return userDao.checkEmail(email);
    }

    public List<FriendRequest> findFriendRequestByUsernameAndStatus(User sender) {
        return userDao.getAllFriendRequestByUsernameAndStatus(sender);
    }

    @Override
    public FriendRequest findFriendRequestById(long id) {
        return userDao.findFriendRequestById(id);
    }

    @Override
    public void updateFriendRequest(FriendRequest friendRequest) {
        userDao.updateFriendRequest(friendRequest);
    }

    @Override
    public void deleteFriendRequest(FriendRequest friendRequest) {
        userDao.deleteFriendRequest(friendRequest);
    }

    @Override
    public void sendResetLink(String link, String toUser) {
        userDao.sendResetLink(link, toUser);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public List<User> getUserHavingBirthDayToday(int month, int day) {
        return userDao.getUserHavingBirthDayToday(month, day);
    }
}
