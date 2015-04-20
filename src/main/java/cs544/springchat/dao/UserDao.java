/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs544.springchat.dao;

import cs544.springchat.domain.User;

/**
 *
 * @author suraj
 */
 
public interface UserDao {
    
    User findByUserName(String username);
 
}