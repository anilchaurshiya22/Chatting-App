package com.springchat.controller;

import com.springchat.validator.ConfirmPasswordValidator;
import com.springchat.domain.User;
import com.springchat.domain.UserRoles;
import com.springchat.service.UserService;
import com.springchat.util.GenerateRandomNumber;
import com.springchat.util.PasswordEncoderGenerator;
import com.springchat.validator.EmailValidator;
import com.springchat.validator.UniqueUsernameValidator;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String formProcess(@Valid @ModelAttribute("user") User user, BindingResult bres) {
        confirmPasswordValidator.validate(user, bres);
        UniqueUsernameValidator.validate(user, bres); 
        emailValidator.setIndex(0);
        emailValidator.validate(user, bres);
        if (bres.hasErrors()) {
            return "register";
        }
        user.setPassword(PasswordEncoderGenerator.beCryptPassword(user.getPassword()));
        user.setActive(true);
        List<UserRoles> userRoles = new ArrayList<>();
        UserRoles userRole = new UserRoles();
        userRole.setRole("ROLE_USER");
        userRoles.add(userRole);
        userRole.setUser(user); 
        user.setUserRoles(userRoles);
       
        userService.insertNewUser(user);
        return "redirect:/login";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute("user") User user, BindingResult bres, RedirectAttributes redirectAttributes) {
        if (bres.hasErrors()) {
            return "edit";
        }
        userService.updateUser(user);
        return "redirect:/index";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editProfile(@ModelAttribute("user") User user,Model model,Principal principal) throws ParseException {
        model.addAttribute("userVal",userService.findUserByUsername(principal.getName()));
        return "edit";
    }

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
    public String forgetPassword(@ModelAttribute("user") User user) {
        return "forgetPassword";
    }

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    public String forgetPassword(@ModelAttribute("user") User user, BindingResult bres) {
        emailValidator.setIndex(1);
        emailValidator.validate(user, bres);
        if (bres.hasErrors()) {
            return "forgetPassword";
        }
        String randomNumber = GenerateRandomNumber.getRamdomNumber();
        String link = "forgetPassword/" + user.getEmail() + "/" + randomNumber;
        userService.sendResetLink(link, user.getEmail());
        User currUser = userService.getUserByEmail(user.getEmail());
        currUser.setTokenValue(randomNumber);
        userService.updateUser(currUser);
        return "redirect:/login";
    }

    @RequestMapping(value = "/forgetPassword/{email}/{randomNumber}", method = RequestMethod.GET)
    public String resetLink(@ModelAttribute("user") User user,@PathVariable String email, @PathVariable String randomNumber, Model model) {
        User currUser = userService.getUserByEmail(email);
        if(currUser == null || currUser.getTokenValue() == null || !currUser.getTokenValue().equals(randomNumber)){
            return "redirect:/403";
        }
        return "/resetPassword";
    }

    @RequestMapping(value = "/forgetPassword/{email}/resetPassword", method = RequestMethod.POST)
    public String resetPassword(@ModelAttribute("user") User user, @PathVariable String email, BindingResult bres) { 
        confirmPasswordValidator.validate(user, bres);
        if (bres.hasErrors()) {
            return "/resetPassword";
        }
        User currUser = userService.getUserByEmail(email);
        currUser.setPassword(PasswordEncoderGenerator.beCryptPassword(user.getPassword()));
        userService.updateUser(currUser);
        return "redirect:/login";
    }

}
