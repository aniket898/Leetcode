/*Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
Input : arr[] = {10, 2, -2, -20, 10}, 
        k = -10
Output : 3
Subarrays: arr[0...3], arr[1...4], arr[3..4]
have sum exactly equal to -10.

Input : arr[] = {9, 4, 20, 3, 10, 5},
            k = 33
Output : 2
Subarrays : arr[0...2], arr[2...4] have sum
exactly equal to 33.A simple solution is to traverse all the subarrays and calculate their sum. If sum is equal to the required sum then increment the count of subarrays. Print final count of subarrays.

An efficient solution is while traversing the array, store sum so far in currsum. Also maintain count of different values of currsum in a map. If value of currsum is equal to desired sum at any instance increment count of subarrays by one. The value of currsum exceeds desired sum by currsum – sum. If this value is removed from currsum then desired sum can be obtained. From the map find number of subarrays previously found having sum equal to currsum-sum. Excluding all those subarrays from current subarray, gives new subarrays having desired sum. So increase count by the number of such subarrays. Note that when currsum is equal to desired sum then also check number of subarrays previously having sum equal to 0. Excluding those subarrays from current subarray gives new subarrays having desired sum. Increase count by the number of subarrays having sum 0 in that case.*/

class Solution {
    public int subarraySum(int[] arr, int sum) {
        // HashMap to store number of subarrays  
        // starting from index zero having   
        // particular value of sum.  
        HashMap <Integer, Integer> prevSum = new HashMap<>();  
        int n = arr.length;
        int res = 0;  
        
        // Sum of elements so far.  
        int currsum = 0;  
        
        for (int i = 0; i < n; i++) {  
        
            // Add current element to sum so far.  
            currsum += arr[i];  
        
            // If currsum is equal to desired sum,  
            // then a new subarray is found. So  
            // increase count of subarrays.  
            if (currsum == sum)   
                res++;          
        
            // currsum exceeds given sum by currsum   
            //  - sum. Find number of subarrays having   
            // this sum and exclude those subarrays  
            // from currsum by increasing count by   
            // same amount.  
            if (prevSum.containsKey(currsum - sum))   
                res += prevSum.get(currsum - sum);  
                
        
            // Add currsum value to count of   
            // different values of sum.  
            Integer count = prevSum.get(currsum); 
            if (count == null) 
                prevSum.put(currsum, 1); 
            else
                prevSum.put(currsum, count+1);  
        }  
        
        return res; 
        
    }
}