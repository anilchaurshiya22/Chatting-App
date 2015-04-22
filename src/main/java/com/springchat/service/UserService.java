package com.springchat.service;

import com.springchat.domain.FriendRequest;
import com.springchat.domain.User;
import java.util.List;

/**
 *
 * @author Devil
 */
public interface UserService {

    public User findUserById(long id);
//
//    public List<User> getAllUsers();

    public User findUserByUsername(String username);

    public void insertNewUser(User user);

    public void updateUser(User user);

    public User checkEmail(String email);

    public void addFriendRequest(FriendRequest request);

    public FriendRequest findFriendRequestByReceiverAndSender(User receiver, User sender);

    public FriendRequest findFriendRequestByReceiverEmailAndSender(String email, User sender);
    
    public List<FriendRequest> findFriendRequestByUsernameAndStatus(User sender);

    public void sendResetLink(String link,String toUser);

    public User getUserByEmail(String email);

    public List<FriendRequest> getAllFriendRequestByUsernameAndStatus(User sender);

    public FriendRequest findFriendRequestById(long id);

    public void updateFriendRequest(FriendRequest friendRequest);

    public void deleteFriendRequest(FriendRequest friendRequest);
    
    public List<User> getUserHavingBirthDayToday(int month, int day);
}
