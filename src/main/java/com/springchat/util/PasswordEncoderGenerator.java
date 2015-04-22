package com.springchat.util;

/**
 *
 * @author 984350
 */


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {

    public static String beCryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }
<<<<<<< HEAD
//    public static void main(String[] args) {
//        System.out.println(beCryptPassword("asdfasdf"));
//    }

=======

    public static void main(String[] args) {
        System.out.println(beCryptPassword("anil"));
    }
>>>>>>> ae293af16735599c5be7ea966174b4849f05262f
}



