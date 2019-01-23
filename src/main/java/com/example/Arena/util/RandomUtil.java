package com.example.Arena.util;

import java.util.Random;

public class RandomUtil {
    public int random(int min, int max) {
        Random r = new Random();

        return r.nextInt((max - min) + 1) + min;
    }
}
