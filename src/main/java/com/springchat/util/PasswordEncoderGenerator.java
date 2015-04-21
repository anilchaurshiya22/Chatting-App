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

    public static void main(String[] args) {
        System.out.println(beCryptPassword("anil"));
    }
}



