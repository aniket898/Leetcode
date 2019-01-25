package com.aniket.test;

import java.util.*;

public class WordLadder {
    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        Set<String> wordList = new HashSet<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        ladderLength(beginWord, endWord, wordList);
    }

    public static int ladderLength(String start, String end, Set<String> dict) {
        if (start == null || start.isEmpty() || end == null || end.isEmpty())
            return 0;

        int length = 1;
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);

        HashSet<String> visited = new HashSet<String>();

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                for (int j = 0; j < curr.length(); j++) {
                    char[] charCurr = curr.toCharArray();
                    for (char c = 'a'; c < 'z'; c++) {
                        charCurr[j] = c;  // change one character at a time
                        String strCurr = new String(charCurr);
                        if (strCurr.equals(end)) {
                            return length + 1;
                        } else {
                            if (dict.contains(strCurr) && !visited.contains(strCurr)) {
                                queue.offer(strCurr);
                                visited.add(strCurr);
                            }
                        }
                    }
                }
            }
            length++;
        }
        return 0;
    }
}
