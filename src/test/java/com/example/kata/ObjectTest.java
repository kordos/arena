package com.example.kata;

import org.junit.Test;

public class ObjectTest {
    @Test
    public void testObjectsComparision() {
        System.out.println("x" == "x");
        System.out.println(new String("x") == new String("x"));
        System.out.println(new String("x").equals(new String("x")));
        System.out.println(new String("x") == "x");
        System.out.println(new Integer(7) == new Integer(7));
        System.out.println(new Integer(7) == 7);
    }

}