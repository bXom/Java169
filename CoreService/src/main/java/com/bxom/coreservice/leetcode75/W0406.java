package com.bxom.coreservice.leetcode75;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class W0406 {
    /**
     * leetcode-98
     * https://leetcode.cn/problems/validate-binary-search-tree/
     *
     * @param args
     */
    public static void main(String[] args) {
        log.info("result: {}", isValidBST(new TreeNode()));
    }

    public static boolean solution1(TreeNode root) {
        if (root == null) return true;
        boolean result = true;
        if (root.left != null) {
            result = compare(root.left, root.val, Long.MIN_VALUE);
        }
        if (root.right != null) {
            result = result && compare(root.right, Long.MAX_VALUE, root.val);
        }
        return result;
    }

    private static boolean compare(TreeNode root, long max, long min) {
        if (root == null) return true;
        boolean result = root.val < max && root.val > min;
        if (root.left != null) {
            result = result && compare(root.left, root.val, min);
        }
        if (root.right != null) {
            result = result && compare(root.right, max, root.val);
        }
        return result;
    }

    public static boolean isValidBST(TreeNode root) {
        return solution1(root);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
