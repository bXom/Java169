package com.bxom.coreservice.leetcode75;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class W0306 {
    // 试题
    // leetcode-102
    // https://leetcode.cn/problems/binary-tree-level-order-traversal/
    public static void main(String[] args) {
        levelOrder(new TreeNode(1)).forEach(item -> {
            StringBuilder sb = new StringBuilder();
            item.forEach(i -> sb.append(i).append(","));
            log.info("{}", sb.substring(0, sb.length() - 1));
        });
    }

    public static List<List<Integer>> solution1(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        Map<TreeNode, Integer> deepMap = new HashMap<>();
        deepMap.put(root, 0);
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            int deep = deepMap.get(node);
            if (node.left != null) {
                q.offer(node.left);
                deepMap.put(node.left, deep + 1);
            }
            if (node.right != null) {
                q.offer(node.right);
                deepMap.put(node.right, deep + 1);
            }
            if (result.size() < deep + 1) result.add(new ArrayList<>());
            result.get(deep).add(node.val);
        }
        return result;
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        return solution1(root);
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
