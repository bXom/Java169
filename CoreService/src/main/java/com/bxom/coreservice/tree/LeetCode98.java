package com.bxom.coreservice.tree;

import java.util.ArrayList;
import java.util.List;

public class LeetCode98 {
    /**
     * 98. 验证二叉搜索树
     * https://leetcode.cn/problems/validate-binary-search-tree/
     *
     * @param args
     */
    public static void main(String[] args) {
        isValidBST(new TreeNode(1));
    }

    public static boolean isValidBST(TreeNode root) {
//        return solution1(root);
        return solution2(root);
    }

    /**
     * 迭代遍历
     *
     * @param root
     * @return
     */
    private static boolean solution2(TreeNode root) {
        return foreach(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean foreach(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        if (root.val <= minVal || root.val >= maxVal) return false;
        return foreach(root.left, minVal, root.val) && foreach(root.right, root.val, maxVal);
    }

    /**
     * 中序遍历，检查结果是否有序
     *
     * @param root
     * @return
     */
    private static boolean solution1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return true;
        foreach(root, list);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i + 1)) return false;
        }
        return true;
    }

    private static void foreach(TreeNode root, List<Integer> list) {
        if (root == null) return;
        foreach(root.left, list);
        list.add(root.val);
        foreach(root.right, list);
    }

    public static class TreeNode {
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
