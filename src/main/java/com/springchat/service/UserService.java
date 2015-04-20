package com.springchat.service;

import com.springchat.domain.User;
import java.util.List;

/**
 *
 * @author Devil
 */
public interface UserService {

//    public User findUserById(long id);
//
//    public List<User> getAllUsers();

    public User findUserByUsername(String username);
}
