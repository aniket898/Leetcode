package com.aniket.test;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMax {
    public static void main(String[] args) {
        maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3);
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        Deque<Integer> deque = new LinkedList<Integer>();
        int[] result = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.getLast()]) {
                deque.removeLast();
            }

            deque.addLast(i);

            // Remove if the size of the deque is greater than k
            if (i - deque.getFirst() + 1 > k) {
                deque.removeFirst();
            }

            // Add into the result
            if (i + 1 >= k) {
                result[i + 1 - k] = nums[deque.getFirst()];
            }
        }

        return result;
    }
}
