package com.bxom.coreservice.tree;

import java.util.ArrayList;
import java.util.List;

public class LeetCode230 {
    /**
     * leetcode-230
     * https://leetcode.cn/problems/kth-smallest-element-in-a-bst/
     *
     * @param args
     */
    public static void main(String[] args) {
        kthSmallest(new TreeNode(1), 1);
    }

    public static int solution1(TreeNode root, int k) {
        List<Integer> sort = new ArrayList<>();
        if (root == null) return -1;
        midForeach(root, sort);
        return sort.get(k - 1);
    }

    private static void midForeach(TreeNode root, List<Integer> list) {
        if (root == null) return;
        midForeach(root.left, list);
        list.add(root.val);
        midForeach(root.right, list);
    }

    public static int kthSmallest(TreeNode root, int k) {
        return solution1(root, k);
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
