package cs544.springchat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author suraj
 */
@Controller
public class ChatController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "guest");
        return "index";
    }
    
    @RequestMapping("/hello")
    public String dashboard(Model model) {
        model.addAttribute("name", "user");
        return "helloview";
    }
    
    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "helloview";
    }
}
