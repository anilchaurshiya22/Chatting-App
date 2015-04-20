/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs544.springchat.service.impl;

import cs544.springchat.dao.UserDao;
import cs544.springchat.domain.UserRole;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    //get user from the database, via Hibernate
    @Autowired
    private UserDao userDao;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        cs544.springchat.domain.User user = userDao.findByUserName(username);
        List<GrantedAuthority> authorities
                = buildUserAuthority(user.getUserRole());

        return buildUserForAuthentication(user, authorities);

    }

	// Converts cs544.springchat.domain.User user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(cs544.springchat.domain.User user,
            List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        for (UserRole userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }

}
