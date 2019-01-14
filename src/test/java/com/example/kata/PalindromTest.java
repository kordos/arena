package com.example.kata;

import org.junit.*;
import static org.junit.Assert.*;

public class PalindromTest {

    @Test
    public void testHavePalindromString_NotPalindrom_ReturnsFalse() {
        String notPalindrom = "abcd";
        Palindrom palindrom = new Palindrom();

        assertFalse(palindrom.havePalindromString(notPalindrom));
    }

    @Test
    public void testHavePalindromString_IsPalindrom_ReturnsTrue() {
        String palindromStr = "abcba";
        Palindrom palindrom = new Palindrom();

        assertTrue(palindrom.havePalindromString(palindromStr));

        assertTrue(palindrom.havePalindromString("xabba"));
        assertTrue(palindrom.havePalindromString("axbba"));
        assertTrue(palindrom.havePalindromString("axbba"));
        assertTrue(palindrom.havePalindromString("abxba"));

        assertTrue(palindrom.havePalindromString("abbxa"));
    }
}
