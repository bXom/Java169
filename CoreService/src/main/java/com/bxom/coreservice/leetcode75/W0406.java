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

    public static boolean solution(TreeNode root) {
        return false;
    }

    public static boolean isValidBST(TreeNode root) {
        return solution(root);
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
