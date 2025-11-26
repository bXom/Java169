package com.bxom.coreservice.leetcode75.W5;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class W0505 {
    /**
     * leetcode-236
     * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/description/
     *
     * @param args
     */
    public static void main(String[] args) {
        log.info("result: {}", lowestCommonAncestor(new TreeNode(1), new TreeNode(1), new TreeNode(1)));
    }

    public static TreeNode solution(TreeNode root, TreeNode p, TreeNode q) {
        return root;
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return solution(root, p, q);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
