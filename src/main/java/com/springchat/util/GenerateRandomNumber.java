/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springchat.util;

import java.util.Random;
import java.util.UUID;
import org.apache.commons.lang.math.JVMRandom;

/**
 *
 * @author 984350
 */
public class GenerateRandomNumber {
    public static String getRamdomNumber(){
        return UUID.randomUUID().toString();
    }
}
