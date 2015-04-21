package com.springchat.controller;

import com.springchat.validator.ConfirmPasswordValidator;
import com.springchat.domain.User;
import com.springchat.service.UserService;
import com.springchat.util.PasswordEncoderGenerator;
import com.springchat.validator.EmailValidator;
import com.springchat.validator.UniqueUsernameValidator;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author 984350
 */
@Controller
public class UserController {

    @Autowired
    private ConfirmPasswordValidator confirmPasswordValidator;
    @Autowired
    private UniqueUsernameValidator UniqueUsernameValidator;
    @Autowired
    private EmailValidator emailValidator;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String user(@ModelAttribute("user") User user) {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String formProcess(@Valid @ModelAttribute("user") User user, BindingResult bres,RedirectAttributes redirectAttributes) {
            confirmPasswordValidator.validate(user, bres);
         //   UniqueUsernameValidator.validate(user, bres); 
            emailValidator.setIndex(0);
            emailValidator.validate(user, bres);
        if (bres.hasErrors()) {
            return "register";
        }
        user.setPassword(PasswordEncoderGenerator.beCryptPassword(user.getPassword())); 
        redirectAttributes.addFlashAttribute("userVal", user);
         userService.insertNewUser(user);
       // return "redirect:/login";
       return "edit";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute("user") User user, BindingResult bres,RedirectAttributes redirectAttributes) {
        if (bres.hasErrors()) {
            return "edit";
        }
       userService.updateUser(user);
       return "redirect:/login";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editProfile(@ModelAttribute("user") User user){
        return "edit";
    }
    
}
