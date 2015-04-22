package com.springchat.util;

import com.springchat.domain.User;
import com.springchat.service.UserService;
import com.springchat.serviceImpl.MailService;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author 984351
 */
@Component
public class BirthDayAlertSender {

    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;

    @Scheduled(cron = "0 0 12  * * *")
    public void sendBirthDayAlert() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        System.out.println("***********start finding*************");
        List<User> users = userService.getUserHavingBirthDayToday(month, day);
        System.out.println("users=====----------------" + users);
        if (users != null) {
            for (User user : users) {
                if (user.getEmail() != null) {
                    mailService.sendBirthMailMail(user.getEmail(), "Birth Day Alert", "Dear " + user.getUsername() + " Wish you Happy Birth Day ");
                }
            }
        }

    }

}
