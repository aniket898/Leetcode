package com.aniket.test;

public class ReverseStringWords {
    public static void main(String[] args) {
        //System.out.print(reverseWords("the sky is blue".toCharArray()));
        System.out.print(reverseWords("the sky is blue"));
    }

    public static String reverseWords(String s) {
        // empty string
        if (s == null || s.length() == 0) return "";

        int length = s.length();
        StringBuilder r = new StringBuilder(); // buffer to store the reversed string
        int stringStart = 0;
        int tokenReadPos = length - 1;
        int wordEnd;

        // Check the leading spaces
        while (stringStart < length && s.charAt(stringStart) == ' ')
            stringStart++;

        // Determine the trailing spaces
        while (tokenReadPos >= stringStart && s.charAt(tokenReadPos) == ' ') {
            tokenReadPos--;
        }

        while (tokenReadPos >= stringStart) {
            if (s.charAt(tokenReadPos) == ' ') {
                r.append(' ');
                tokenReadPos--;
                // Handle multiple spaces
                while (tokenReadPos >= stringStart && s.charAt(tokenReadPos) == ' ')
                    tokenReadPos--;
            }
            else {
                wordEnd = tokenReadPos;
                while (tokenReadPos >= stringStart && s.charAt(tokenReadPos) != ' ') {
                    tokenReadPos--;
                }
                // Copy a word into the buffer
                for (int i = tokenReadPos + 1; i <= wordEnd; i++)
                    r.append(s.charAt(i));
            }
        }
        return r.toString();
    }

//    public static String reverseWords(char[] s) {
//        if (s == null || s.length <= 1) {
//            return new String(s);
//        }
//
//        int i = 0;
//        int j = s.length - 1;
//        while (i < j) {
//            swap(s, i, j);
//            i++;
//            j--;
//        }
//
//        // Step 2: swap again within a token
//        i = 0;
//        j = 0;
//        while (j < s.length) {
//            while (j < s.length && s[j] != ' ') {
//                j++;
//            }
//
//            int m = i;
//            int n = j - 1;
//            while (m < n) {
//                swap(s, m, n);
//                m++;
//                n-- ;
//            }
//
//            i = j + 1;
//            j = i;
//        }
//        return new String(s);
//    }
//
//    private static void swap(char[] s, int i, int j) {
//        char tmp = s[i];
//        s[i] = s[j];
//        s[j] = tmp;
//    }
}