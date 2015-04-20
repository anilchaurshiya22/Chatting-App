/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs544.springchat.domain;

/**
 *
 * @author suraj
 */
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private int id;
    
    @Column(name = "username", unique = true, nullable = false, length = 45)
    private String username;
    
    @Column(name = "password", nullable = false, length = 60)
    private String password;
    
    private String email;
    
    @Column(name = "enabled", nullable = false)
    private boolean enabled;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserRole> userRole = new HashSet<>(0);

    public User() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRole> getUserRole() {
        return this.userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }
}
