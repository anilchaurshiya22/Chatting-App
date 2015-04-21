package com.springchat.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author 984351
 */
public class RandomGenerator {

    public static String generateRandomNumber() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        String result = "";
        for (int i = 0; i < 4; i++) {
            result += numbers.get(i).toString();
        }
        return result;
    }
}
