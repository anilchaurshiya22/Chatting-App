package com.springchat.validator;

/**
 *
 * @author 984350
 */
import com.springchat.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ConfirmPasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> paramClass) {
        return User.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        User user = (User) obj;

        if (user.getPassword() == null || user.getConfirmPassword() == null || !user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword",
                    "password.mismatch");
        }

    }
}
