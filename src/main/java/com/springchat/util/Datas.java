/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springchat.util;

/**
 *
 * @author 984350
 */
public class Datas {

    private static String link = "<a href = 'http://localhost:8080/SpringChat/{link}'>Reset Password</a>";
    public static String html = "<html<head></head><body>" + link + "</body></html>";

    private static String accept = "<a href = 'http://localhost:8080/SpringChat/friendRequests/accept/{link}'>Accept</a>";
    private static String deny = "<a href = 'http://localhost:8080/SpringChat/friendRequests/deny/{link}'>Deny</a>";
    public static String frenReqHtml = "<html<head></head><body>" + accept + "|" + deny + "</body></html>";
            
}
