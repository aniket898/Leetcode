/*Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.

Example 1:

Input: "eceba"
Output: 3
Explanation: t is "ece" which its length is 3.
Example 2:

Input: "ccaabbb"
Output: 5
Explanation: t is "aabbb" which its length is 5.
*/

package com.aniket.test;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstring2DistinctChars {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringTwoDistinct("abZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ"));
    }

    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character,Integer> map = new HashMap<Character, Integer>();
        int maxlen = 0, left = 0, charType = 0;
        for(int right = 0; right < s.length(); right++){
            char c = s.charAt(right);
            if(map.containsKey(c)){
                map.put(c, map.get(c)+1);
            }else{
                map.put(c,1);
            }
            if(map.get(c) == 1){
                charType++;
            }

            while(charType > 2){
                c = s.charAt(left);
                int count = map.get(c);
                if(count>1){
                    map.put(c, count-1);
                }else {
                    map.remove(c);
                    charType--;
                }
                left++;
            }

            maxlen = Math.max(maxlen, right-left+1);
        }
        return maxlen;
    }
}
/*Solution for K Unique Characters

The following solution is corrected. Given "abcadcacacaca" and 3, it returns "cadcacacaca".
*/

public int lengthOfLongestSubstringKDistinct(String s, int k) {
    int result = 0;
    int i=0;
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
 
    for(int j=0; j<s.length(); j++){
        char c = s.charAt(j);
        if(map.containsKey(c)){
            map.put(c, map.get(c)+1);
        }else{
            map.put(c, 1);
        }
 
        if(map.size()<=k){
            result = Math.max(result, j-i+1);
        }else{
            while(map.size()>k){
                char l = s.charAt(i);
                int count = map.get(l);
                if(count==1){
                    map.remove(l);
                }else{
                    map.put(l, map.get(l)-1);
                }
                i++;
            }
        }
 
    }
 
    return result;
}
Time is O(n).