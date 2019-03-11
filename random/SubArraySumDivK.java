/*Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.

 

Example 1:

Input: A = [4,5,0,-2,-3,1], K = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by K = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
*/

class Solution {
    public int subarraysDivByK(int[] A, int K) {
        HashMap <Integer, Integer> prevSum = new HashMap<>();  
        int n = A.length;
        int res = 0;  
        
        // Sum of elements so far.  
        int currsum = 0;  
        
        for (int i = 0; i < n; i++) {  
        
            currsum = (currsum + A[i]) % K;  
        
            if(currsum < 0) currsum += K;
            if(currsum == 0) res++;    
            if (prevSum.containsKey(currsum))   
                res += prevSum.get(currsum);  
                
        
            Integer count = prevSum.get(currsum); 
            if (count == null) 
                prevSum.put(currsum, 1); 
            else
                prevSum.put(currsum, count+1);  
        }  
        
        return res; 
    }
}