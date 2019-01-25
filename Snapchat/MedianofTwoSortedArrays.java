Leetcode: Median of Two Sorted Arrays
There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Understand the problem:
The problem gives two sorted array, A and B. Find out the median of the two sorted arrays. 
Note that the time complexity is required to be O(log(m+n)).

An initial thought was firstly merge the two arrays, then median is  the number on A.length + B.length - 1 / 2. However, merging will take O(m + n) time, which however the algorithm asks for a solution in log time. So it is naturally to think about the binary search.

One thing must take note is the definition of the median of a sorted array. Note that the return number is double in the problem. So if the array length is even, e.g. 1, 2, 3, 4. The median is the average of 2 and 3, i.e., 2 + 3 / 2 = 2.5

Solution:
http://fisherlei.blogspot.com/2012/12/leetcode-median-of-two-sorted-arrays.html
http://www.lifeincode.net/programming/leetcode-median-of-two-sorted-arrays-java/

The problem is equivalent to find the kth element in the two sorted array. The key is to decide how to eliminate part of the array each recursion. 

Code (Java):

public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return 0.f;
        }
         
        int n1 = nums1.length;
        int n2 = nums2.length;
         
        if ((n1 + n2) % 2 == 1) {
            return findMedianHelper(nums1, nums2, (n1 + n2) / 2 + 1);
        } else {
            return (findMedianHelper(nums1, nums2, (n1 + n2) / 2) + 
                    findMedianHelper(nums1, nums2, (n1 + n2) / 2 + 1)) / 2;
        }
    }
     
    private double findMedianHelper(int[] nums1, int[] nums2, int k) {
        if (nums1 == null || nums1.length == 0) {
            return nums2[k - 1];
        }
         
        if (nums2 == null || nums2.length == 0) {
            return nums1[k - 1];
        }
         
        if (k == 1) {
            return Math.min(nums1[0], nums2[0]);
        }
         
        int n1 = nums1.length;
        int n2 = nums2.length;
         
        if (nums1[n1 / 2] > nums2[n2 / 2]) {
            if ((n1 / 2 + n2 / 2 + 1) >= k) {
                return findMedianHelper(Arrays.copyOfRange(nums1, 0, n1 / 2), nums2, k);
            } else {
                return findMedianHelper(nums1, Arrays.copyOfRange(nums2, n2 / 2 + 1, n2), 
                                        k - (n2 / 2 + 1));
            }
        } else {
            if ((n1 / 2 + n2 / 2 + 1) >= k) {
                return findMedianHelper(nums1, Arrays.copyOfRange(nums2, 0, n2 / 2), k);
            } else {
                return findMedianHelper(Arrays.copyOfRange(nums1, n1 / 2 + 1, n1), 
                                        nums2, k - (n1 / 2 + 1));
            }
        }
    }
}