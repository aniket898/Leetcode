/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.

Example 1:

Input: [3,4,5,1,2] 
Output: 1
Example 2:

Input: [4,5,6,7,0,1,2]
Output: 0
*/

class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
         
        int lo = 0;
        int hi = nums.length - 1;
         
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
             
            if (nums[lo] < nums[hi]) {
                return nums[lo];
            } else if (nums[mid] < nums[hi]) {
                hi = mid;
            } else if (nums[lo] < nums[mid]) {
                lo = mid;
            }
        }
         
        return Math.min(nums[lo], nums[hi]);
    }
}