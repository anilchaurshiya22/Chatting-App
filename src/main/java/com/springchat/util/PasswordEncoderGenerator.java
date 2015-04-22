package com.springchat.util;

/**
 *
 * @author 984350
 */
import java.util.Calendar;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {

    public static String beCryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    public static void main(String[] args) {
//        System.out.println(beCryptPassword("anil"));
        
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        System.out.println("day==" + day);
        System.out.println("month==" + month);
//        ---------------------------
//            String format1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
//        String[] datearr = format1.split("-");
//        String day = datearr[0];
//        String month = datearr[1];
    }
}
