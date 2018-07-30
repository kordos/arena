package com.example.Arena;

import java.util.Random;

public class RandomUtil {
    int random(int min, int max) {
        Random r = new Random();

        return r.nextInt((max - min) + 1) + min;
    }
}
