package com.springchat.controller;

import com.springchat.domain.FriendRequest;
import com.springchat.domain.User;
import com.springchat.service.UserService;
import com.springchat.serviceImpl.MailService;
import com.springchat.util.Datas;
import com.springchat.util.EmailValidator;
import com.springchat.util.RandomGenerator;
import com.springchat.util.SecurityUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author 984351
 */
@Controller
public class FriendRequestController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;
    @Autowired
    private SecurityUtil securityUtil;

    @RequestMapping(value = "/sendRequest", method = RequestMethod.GET)
    public String sendRequest(@ModelAttribute("friendRequest") FriendRequest friendRequest) {
        return "friendRequest";
    }

    @RequestMapping(value = "/sendRequest", method = RequestMethod.POST)
    public String sendRequest(@Valid @ModelAttribute("friendRequest") FriendRequest friendRequest, BindingResult result, RedirectAttributes attributes) {
        String username = friendRequest.getInviteCode();
        String message = friendRequest.getMessage();

        User user = userService.findUserByUsername(username);

        User currentUser = securityUtil.getSessionUser();

        if (username.equals(currentUser.getUsername())) {
            attributes.addFlashAttribute("message", "Sorry!!!,You can not send request to your self.");
            return "redirect:/sendRequest";
        }

        if (user == null) {
            if (!EmailValidator.isValidEmailAddress(username)) {
                attributes.addFlashAttribute("message", "Sorry!!!,User not found");
                return "redirect:/sendRequest";
            } else {
                String randInviteCode = RandomGenerator.generateRandomNumber();
                FriendRequest fr = userService.findFriendRequestByReceiverEmailAndSender(username, currentUser);
                if (fr != null) {
                    attributes.addFlashAttribute("message", "Sorry!!!,You have already send request to " + username + " .");
                    return "redirect:/sendRequest";
                }
                friendRequest.setInviteCode(randInviteCode);
                friendRequest.setReceiverEmail(username);
                //send Email to new Request.
                String subject = currentUser.getUsername() + " invite you to SpringChatPro.";
                mailService.sendMail(currentUser.getUsername(), username, subject, message);
            }
        } else {
            FriendRequest fr = userService.findFriendRequestByReceiverAndSender(user, currentUser);
            if (fr != null) {
                attributes.addFlashAttribute("message", "Sorry!!!,You have already send request to " + username + " .");
                return "redirect:/sendRequest";
            }
            friendRequest.setReceiver(user);
            friendRequest.setInviteCode("");
        }

        friendRequest.setSendDate(new Date());
        friendRequest.setSender(currentUser);
        friendRequest.setStatus(new Character('N'));
        userService.addFriendRequest(friendRequest);
        attributes.addFlashAttribute("message", "Friend Request Sent to:" + username);
        return "redirect:/sendRequest";
    }

    @RequestMapping(value = "/friendRequests", method = RequestMethod.GET)
    public String getFriendRequestList(Model model) {
        User currentUser = securityUtil.getSessionUser();
        List<FriendRequest> friendRequests = userService.getAllFriendRequestByUsernameAndStatus(currentUser);
        model.addAttribute("friendRequests", friendRequests);
        return "dashboard";
    }

    @RequestMapping(value = "friendRequests/accept", method = RequestMethod.POST)
    public String acceptFriendRequest( int id, RedirectAttributes redirectAttributes) {
        FriendRequest friendRequest = userService.findFriendRequestById(id);
        User friend = userService.findUserById(friendRequest.getSender().getId());
        System.out.println("friend==" + friend.getUsername());
        User currentUser = securityUtil.getSessionUser();
        List<User> users = currentUser.getFriends();

        users.add(friend);
        List<User> otherUserFriends = friend.getFriends();

        otherUserFriends.add(currentUser);
        currentUser.setFriends(users);
        friend.setFriends(otherUserFriends);

        friendRequest.setStatus(new Character('Y'));
        userService.updateFriendRequest(friendRequest);
        userService.updateUser(currentUser);
        userService.updateUser(friend);

        //display this message in page
        redirectAttributes.addFlashAttribute("message", "You become friends with " + friend.getUsername());
//        model.addAttribute("message", "You become friends with " + friend.getUsername());
        return "redirect:/friendRequests";

    }

    @RequestMapping(value = "friendRequests/decline", method = RequestMethod.POST)
    public String declineFriendRequest( int id, RedirectAttributes redirectAttributes) {
        FriendRequest friendRequest = userService.findFriendRequestById(id);
        userService.deleteFriendRequest(friendRequest);
        return "redirect:/friendRequests";
    }

}
