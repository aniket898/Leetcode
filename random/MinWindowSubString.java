//Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
//        For example,
//        S = "ADOBECODEBANC"
//        T = "ABC"
//        Minimum window is "BANC".
//        Note:
//        If there is no such window in S that covers all characters in T, return the emtpy string "".
//        If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
//        Understand the problem:
//        The problem asks for finding the minimum window substring. Note that the time complexity should be in O(n) time.
//
//        Naive Approach:
//        A very straight-forward solution could be save the characters of T into a hashset, then iterate though the string from beginning. When found an existed character at the hashset, we delete that one until the hashset is empty, which means we found the string that contains all the characters in T. Note that the T string could contain duplicated characters, so we must use a hash map to save its number of occurrence. If the number of occurrence equals to 1, we delete that key.
//
//        For this algorithm, it will take O(n^2) time because we found all substrings that fulfill the requirements, of each times O(n) time.


        package com.aniket.test;

import java.util.HashMap;

public class MinWindowSubString {
    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC","ABC"));
    }

    public static String minWindow(String S, String T) {
        if (S == null || S.length() == 0 || T == null || T.length() == 0) {
            return "";
        }

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        HashMap<Character, Integer> dict = new HashMap<Character, Integer>();

        for (int i = 0; i < T.length(); i++) {
            map.put(T.charAt(i), 0);

            if (dict.containsKey(T.charAt(i))) {
                dict.put(T.charAt(i), dict.get(T.charAt(i)) + 1);
            } else {
                dict.put(T.charAt(i), 1);
            }
        }

        int start = 0;
        int count = 0;
        int minLen = S.length() + 1;
        String result = "";

        for (int end = 0; end < S.length(); end++) {
            if (map.containsKey(S.charAt(end))) {
                map.put(S.charAt(end), map.get(S.charAt(end)) + 1);

                if (map.get(S.charAt(end)) <= dict.get(S.charAt(end))) {
                    count++;
                }
            }
            //ADOBECODEBANC
            if (count == T.length()) {
                while (!dict.containsKey(S.charAt(start)) ||
                        map.get(S.charAt(start)) > dict.get(S.charAt(start))) {
                    if (map.containsKey(S.charAt(start))) {
                        map.put(S.charAt(start), map.get(S.charAt(start)) -1);
                    }
                    start++;
                }

                if (end - start + 1 < minLen) {
                    minLen = end - start + 1;
                    result = S.substring(start, end + 1);
                }
            }
        }

        return result;
    }
}


// O(n^2) solution below
//public class Solution {
//    public String minWindow(String S, String T) {
//        if (S == null || S.length() == 0 || T == null || T.length() == 0) {
//            return "";
//        }
//
//        HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
//        for (int i = 0; i < T.length(); i++) {
//            if (hashMap.containsKey(T.charAt(i))) {
//                int freq= hashMap.get(T.charAt(i)) + 1;
//                hashMap.put(T.charAt(i), freq);
//            } else {
//                hashMap.put(T.charAt(i), 1);
//            }
//        }
//
//        int start = 0;
//        int min = Integer.MAX_VALUE;
//        String result = "";
//        while (start < S.length()) {
//            HashMap<Character, Integer> currMap = new HashMap<Character, Integer>(hashMap);
//            int end = start;
//
//            while (end < S.length() && !currMap.isEmpty()) {
//                if (currMap.containsKey(S.charAt(end))) {
//                    if (currMap.get(S.charAt(end)) == 1) {
//                        currMap.remove(S.charAt(end));
//                    } else {
//                        int freq = currMap.get(S.charAt(end));
//                        currMap.put(S.charAt(end), freq - 1);
//                    }
//                }
//                end++;
//            }
//
//            if (currMap.isEmpty() && (end - start < min)) {
//                min = end - start;
//                result = S.substring(start, end);
//            }
//
//            start++;
//        }
//
//        return result;
//    }
//}