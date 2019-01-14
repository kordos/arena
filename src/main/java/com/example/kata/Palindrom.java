package com.example.kata;

public class Palindrom {

    public String getPalindromFromString(String data) {
        String result = "";


        return result;
    }

    public boolean havePalindromString(String data) {

        for (int i = 0; i < data.length(); i++) {
            String withoutOne = removeCharAt(i, data);

            String partsOne = withoutOne.substring(0, withoutOne.length() / 2);
            String partsTwo = withoutOne.substring(withoutOne.length() / 2);

            String reverted = revertString(partsTwo);
            if (partsOne.equals(reverted)) {
                return true;
            }

            String dd = "";
        }

        return false;
    }

    private String removeCharAt(int index, String data) {
        if (index == 0)
            return data.substring(index +1);
        else
            return data.substring(0, index) + data.substring(index+1);
    }

    private String revertString(String data) {
        String result = "";

        for (int i = data.length(); i > 0; i--) {
            String part = data.substring(i-1, i);
            result += part;
        }

        return result;
    }
}
