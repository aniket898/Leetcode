Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false




Understand the Problem:
The problem asks for giving a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words. 

As a first glance of the problem, it asks for partition a word s, such that both its two parts are included in the dictionary. 

Wrong Solution:
A straight-forward solution could be developed using iterative approach. We partition the word into two parts, and check both of the two parts are included in the dictionary. We start from the first character of the string, and check the both halves. If yes, return true, else go to the next character. 

Wrong Code (Java):

public class Solution {
    public boolean wordBreak(String s, Set<string> dict) {
        if (s.isEmpty()) return true;
         
        int len = s.length();
         
        for (int i = 0; i < len; i++) {
            String s1 = s.substring(0, i);
            String s2 = s.substring(i, len);
             
            if (s1.isEmpty() && dict.contains(s2)) return true;
             
            if (dict.contains(s1) && dict.contains(s2)) {
                return true;
            }
        }
        return false;
    }
}

Why the solution above is wrong? Let's see an output at first:
Input:	"abcd", ["a","abc","b","cd"]
Output:	false
Expected:	true
Now you might get the reason. We only considered partitioning the string into TWO parts. However, the problem asked for partition the word into one or MORE substrings. Knowing that, the problem becomes not that easy. 

Let's rethink the naive solution above, why it is not doable?
If we partition the string into two substrings, the complexity is O(n)
For three parts, it is O(n^2)
For four parts, it is O(n^3)
For a string with length n, the maximal possible partitions is n+1 substrings, i.e., each character is a substring plus a empty string. So the overall time complexity is bounded by O(n^(n), which is unable to solve in polynomial time. So we must come out a practical solution.



Correct Solution(Recursive):
Note that in the above solution, we only considered the string to be partitioned into two halves. To address this, we could use DFS in recursive manner. For the substring [0, i) is contained in the dictionary, we recursively start from [i, len) to check the rest of it. 

Code (Java):
public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        if (dict.contains(s)) return true;
         
        int len = s.length();
         
        for (int i = 1; i < len; i++) {
            String s1 = s.substring(0, i);
            if (dict.contains(s1)) {
                String s2 = s.substring(i, len);
                if (wordBreak(s2, dict) == true) return true;
            }
        }
        return false;
    }
}

The solution itself has no problem. However, it will result in Time Limit Exceed at OJ. Let's analyze the time complexity of this solution. For each recursion, we have to check its left and right halves, so two operations per recursion. We do this recursion O(n) times. So the total time complexity is O(2^n). Of course it will exceed the time limit. 

Before we jump into an accepted solution, let's see another solution. Basically it is the same as above, but used the dictionary to compare with the string.

Alternative Solution (Java):

public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        return wordBreak(s, dict, 0);   
    }
     
    private boolean wordBreak(String s, Set<String> dict, int start) {
        if (start == s.length()) return true;
         
        for (String word : dict) {
            int len = word.length();
             
            if (start + len >= s.length()) continue;
             
            if (s.substring(start, start + len).equals(word)) {
                if (wordBreak(s, dict, start + len) == true) return true;
            }
        }
        return false;
    }
}
  
Note that it will start result in TLE, but the idea of comparing dictionary word with the string is quite commonly used in many sub-string problems. 


DP Solution:
The key idea of using DP is using a status array dp[] to mark the status such that dp[i] is true means [0, i) is segmented using the dictionary. So s[0, i) can be segmented if and only if s[0, j) can be segmented AND s[j, i) is in the dictionary. 

public class Solution {
    public boolean wordBreak(String s, Set<string> dict) {
        if (s == null || s.isEmpty()) return true;
         
        // dp[i] means [0,i) is breakable
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
         
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j,i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}

Discussion:
So using DP, the time complexity is reduced to O(n^2). The space complexity is O(n) since we used the status array. 

Alternative Solution using DP:

public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || s.isEmpty()) return true;
         
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
         
        for (int i = 0; i < s.length(); i++) {
            if (!dp[i]) continue;
             
            for (String word : dict) {
                int len = word.length();
                int end = i + len;
                if (end > s.length()) continue;
                 
                if (dp[end]) continue;
                 
                if (s.substring(i, end).equals(word)) {
                    dp[end] = true;
                }
            }
        }
        return dp[s.length()];
    }
}

Summary:
This is the first DP problem we ever met. The key of using DP is to cache the intermediate results. Try to understand how DP could reduce the time complexity. 