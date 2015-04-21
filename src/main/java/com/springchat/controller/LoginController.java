package com.springchat.controller;

import com.springchat.domain.User;
import com.springchat.validator.EmailValidator;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Devil
 */
@Controller
public class LoginController {

    @Autowired
    private EmailValidator emailValidator;
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String executeSecurity(ModelMap model, Principal principal) {

        String name = principal.getName();
        model.addAttribute("author", name);
        model.addAttribute("message", "Welcome To Dashboard!!!");
        return "welcome";

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getStarted(ModelMap model) {
        return "login";

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "login";

    }

    @RequestMapping(value = "/loginFailed", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {
        model.addAttribute("error", "Invalid username and password!");
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        model.addAttribute("msg", "You've been logged out successfully.");
        return "login";
    }

    //for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {
        ModelAndView model = new ModelAndView();

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }

        model.setViewName("403");
        return model;

    }
    
    @RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
    public String forgetPassword(@ModelAttribute("user") User user) {
        return "forgetPassword";
    }
    
    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    public String forgetPassword(@ModelAttribute("user") User user, BindingResult bres) {
        emailValidator.setIndex(1);
        emailValidator.validate(user, bres);
        if(bres.hasErrors()){
            return "forgetPassword";
        }
        System.out.println("uuser ------- : " + user.getEmail() + " --- -- === ) "+ user.getTokenValue());
        
        return "redirect:/login";
    }

}
