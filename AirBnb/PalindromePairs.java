Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:

Input: ["abcd","dcba","lls","s","sssll"]
Output: [[0,1],[1,0],[3,2],[2,4]] 
Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
Example 2:

Input: ["bat","tab","cat"]
Output: [[0,1],[1,0]] 
Explanation: The palindromes are ["battab","tabbat"]


package com.aniket.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalindromePairs {
    public static void main(String[] args) {
        String[] words = {"bat", "tab", "cat"};
        for(List<Integer> pair :palindromePairs(words)){
            System.out.println("Pair Found:");
            pair.forEach(x -> System.out.print(x + ","));
        }

    }

    public static List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();

        if (words == null || words.length == 0) {
            return result;
        }
        // step1: put the reverse order of the words into a map
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String rWord = reverse(words[i]);
            map.put(rWord, i);
        }

        // step 2 & 3: check the prefix of each word.
        // It can form a palindrome iff prefix in the map AND
        // rest substring is a palindrome.
        // e.g. abll, if the prefix is ab, and the map contains a "ab", then
        // we can form a palindrome which is abllba

        // check the postfix of each word.
        // The word can form a palindrome iff postfix is in the map AND
        // the rest of prefix is a palindrome
        // e.g. llab, where the postfix is ab, if the map cotnains a "ab", then
        // we can form a palindrome of ballab
        for (int j = 0; j < words.length; j++) {
            String word = words[j];

            // get prefix of each word
            for (int i = 0; i <= word.length(); i++) {
                String prefix = word.substring(0, i);
                String rest = word.substring(i);
                if(map.containsKey(prefix) && isPalindrome(rest) &&
                        map.get(prefix) != j){
                    List<Integer> curr = new ArrayList<>();
                    curr.add(j);
                    curr.add(map.get(prefix));
                    result.add(curr);
                }
            }


            // get postfix of each word
            for (int i = word.length(); i > 0; i--) {
                String postfix = word.substring(i);
                String rest = word.substring(0, i);
                if (map.containsKey(postfix) && isPalindrome(rest) &&
                        map.get(postfix) != j) {
                    List<Integer> curr = new ArrayList<>();
                    curr.add(map.get(postfix));
                    curr.add(j);
                    result.add(curr);
                }
            }
        }

        return result;
    }

    private static String reverse(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        char[] array = s.toCharArray();
        int start = 0;
        int end = array.length - 1;

        while (start < end) {
            swap(start, end, array);
            start++;
            end--;
        }

        return new String(array);
    }

    private static void swap(int start, int end, char[] array) {
        char temp = array[start];
        array[start] = array[end];
        array[end] = temp;
    }

    private static boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int start = 0;
        int end = s.length() - 1;

        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }
}
