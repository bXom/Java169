package com.bxom.coreservice.leetcode75.W5;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class W0505 {
    /**
     * leetcode-236
     * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/description/
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        TreeNode nodeL = new TreeNode(5);
        node.left = nodeL;
        TreeNode nodeR = new TreeNode(1);
        node.right = nodeR;
        log.info("result: {}", lowestCommonAncestor(node, nodeL, nodeR));
    }

    public static TreeNode solution2(TreeNode root, TreeNode p, TreeNode q) {
        return root;
    }

    public static TreeNode solution1(TreeNode root, TreeNode p, TreeNode q) {
        Map<Integer, List<TreeNode>> map = new HashMap<>();
        foreach(root, new ArrayList<>(), map);
        List<TreeNode> pList = map.get(p.val);
        List<TreeNode> qList = map.get(q.val);
        TreeNode result = null;
        for (int i = 0; i < pList.size() && i < qList.size(); i++) {
            if (pList.get(i) == qList.get(i)) {
                result = pList.get(i);
            } else {
                break;
            }
        }
        return result;
    }

    private static void foreach(TreeNode root, List<TreeNode> parentList, Map<Integer, List<TreeNode>> parentMap) {
        if (root != null) {
            List<TreeNode> pList = new ArrayList<>(parentList);
            pList.add(root);
            parentMap.put(root.val, pList);
            if (root.left != null) {
                foreach(root.left, pList, parentMap);
            }
            if (root.right != null) {
                foreach(root.right, pList, parentMap);
            }
        }
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return solution1(root, p, q);
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
