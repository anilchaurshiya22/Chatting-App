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
public class EmailValidator implements Validator {

    @Autowired
    private UserService userService;
    
    private String[] msg= {"email.already.used","not.valid.email"};
    private int index;
    
    @Override
    public boolean supports(Class<?> paramClass) {
        return User.class.equals(paramClass);
    }
    
    public void setIndex(int index){
        this.index = index;
    }
    
    @Override
    public void validate(Object obj, Errors errors) {
        User user = (User) obj;

        if (user.getEmail() != null) {
            if(userService.checkEmail(user.getEmail()) == null && index == 1){
                errors.rejectValue("email",
                    msg[index]);
            }else if(userService.checkEmail(user.getEmail()) != null && index == 0){
                 errors.rejectValue("email",
                    msg[index]);
            }
        }

    }
}
