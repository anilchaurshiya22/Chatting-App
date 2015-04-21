package com.springchat.validator;

/**
 *
 * @author 984350
 */
import com.springchat.domain.User;
import com.springchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UniqueUsernameValidator implements Validator {

    @Autowired
    private UserService userService;
            
    @Override
    public boolean supports(Class<?> paramClass) {
        return User.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        User user = (User) obj;
        
        if (user.getPassword() == null || !isUsernamePresent(user.getUsername())) {
            errors.rejectValue("username",
                    "user.name.present");
        }

    }
    
    public boolean isUsernamePresent(String username){
        return userService.findUserByUsername(username) == null;
    }
}
