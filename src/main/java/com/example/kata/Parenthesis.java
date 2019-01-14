package com.example.kata;

import java.util.*;

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
 *
 */
public class Parenthesis {

    public boolean isValid(String test) {

        List<Character> stack = new ArrayList<>();

        Map<Character, Character> map = new HashMap<>();
        map.put('[', ']');
        map.put('{', '}');
        map.put('(', ')');

        for (int i = 0; i < test.length(); i++) {
            Character one = test.charAt(i);
            if (one == '(' || one == '{' || one == '[') {
                // opening parenthesis
                stack.add(test.charAt(i));
            } else {
                // closing parenthesis

                int stackSize = stack.size();
                if (stackSize > 0) {
                    // remove
                    Character last = stack.get(stackSize - 1);
                    if (map.get(last) == one) {
                        stack.remove(stackSize - 1);
                        continue;
                    }
                }

                // no matching parenthesis
                return false;
            }
        }

        return stack.size() == 0;
    }
}
