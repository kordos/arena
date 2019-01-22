package com.example.kata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 *
 * Example 1:
 *
 * Input: "()"
 * Output: true
 * Example 2:
 *
 * Input: "()[]{}"
 * Output: true
 * Example 3:
 *
 * Input: "(]"
 * Output: false
 * Example 4:
 *
 * Input: "([)]"
 * Output: false
 * Example 5:
 *
 * Input: "{[]}"
 * Output: true
 */
@RunWith(Parameterized.class)
public class ParenthesisTest {

    private String testCase;
    private boolean result;

    public ParenthesisTest(String tastCase, boolean result) {
        this.testCase = tastCase;
        this.result = result;
    }

    @Test
    public void testIsValidWithParameters() {
        Parenthesis parenthesis = new Parenthesis();

        assertEquals(result, parenthesis.isValid(testCase));
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][] {
                { "{}", true },
                { "()", true },
                { "[]", true },
                { "()[]{}", true },
                { "([)]", false },
                { "{[]}", true },
                { "{", false },
                { "}", false },
                { "(])", false },
        });
    }
}