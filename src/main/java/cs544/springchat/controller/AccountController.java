/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs544.springchat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author suraj
 */
@Controller
public class AccountController {
    
    @RequestMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("name", "user");
        return "account/dashboard";
    }
    
}
