package com.springchat.controller;

import com.springchat.domain.User;
import com.springchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author 984350
 */
@Controller
public class AdminController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/adminController", method = RequestMethod.GET)
    public String getUserList(Model model){
        model.addAttribute("users", userService.getUsers());
        return "adminController";
    }
    
    @RequestMapping(value = "/adminController", method = RequestMethod.POST)
    public String enableDisableUser(int id, Model model,RedirectAttributes redirectAttributes ){
        User user = userService.findUserById(id);
        user.setActive(!user.isActive()); 
        userService.updateUser(user);
        redirectAttributes.addFlashAttribute("users", userService.getUsers());
        return "redirect:/adminController";
    }
}
