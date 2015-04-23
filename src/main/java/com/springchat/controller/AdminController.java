package com.springchat.controller;

import com.springchat.domain.User;
import com.springchat.service.UserService;
import com.springchat.util.SecurityUtil;
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

    @Autowired
    private SecurityUtil securityUtil;

    @RequestMapping(value = "/adminController", method = RequestMethod.GET)
    public String getUserList(Model model) {
        User currentUser = securityUtil.getSessionUser();
        model.addAttribute("users", userService.getUsers(currentUser.getId() ));
        return "adminController";
    }

    @RequestMapping(value = "/adminController", method = RequestMethod.POST)
    public String enableDisableUser(int id, Model model, RedirectAttributes redirectAttributes) {
        User user = userService.findUserById(id);
        user.setActive(!user.isActive());
        userService.updateUser(user);
        User currentUser = securityUtil.getSessionUser();
        redirectAttributes.addFlashAttribute("users", userService.getUsers(currentUser.getId()));
        return "redirect:/adminController";
    }
}
