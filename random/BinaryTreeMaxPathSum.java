/*
Given a non-empty binary tree, find the maximum path sum.

        For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

        Example 1:

        Input: [1,2,3]

        1
        / \
        2   3

        Output: 6
        Example 2:

        Input: [-10,9,20,null,null,15,7]

        -10
        / \
        9  20
        /  \
        15   7

        Output: 42
*/


package com.aniket.test;

public class BinaryTreeMaxPathSum {

    public static void main(String[] args) {
        maxPathSum(null);
    }

    public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
     }

    private static int maxSum = Integer.MIN_VALUE;
    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxPathSumHelper(root);
        return maxSum;
    }

    private static int maxPathSumHelper(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxPathSumHelper(root.left);
        int right = maxPathSumHelper(root.right);

        int arch = left + right + root.val;

        int pathSum = Math.max(root.val, Math.max(left, right) + root.val);

        maxSum = Math.max(maxSum, Math.max(arch, pathSum));

        return pathSum;
    }
}


