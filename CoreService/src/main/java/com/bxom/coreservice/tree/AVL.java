package com.bxom.coreservice.tree;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AVL {
    public static void main(String[] args) {
        AVL avl = new AVL();
        avl.insert(7);
        avl.insert(1);
        avl.insert(12);
        avl.insert(29);
        avl.insert(21);
        avl.insert(39);
        log.info("height: {}", avl.getHeight(avl.root.right, 0));
        log.info("depth: {}", avl.getDepth(avl.root.right));
    }

    static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    private TreeNode root;

    public void insert(int val) {
        TreeNode current = root;
        while (current != null) {
            if (current.value > val) {
                if (current.left == null) {
                    current.left = new TreeNode(val);
                    selfBalance();
                    return;
                }
                current = current.left;
            } else if (current.value < val) {
                if (current.right == null) {
                    current.right = new TreeNode(val);
                    selfBalance();
                    return;
                }
                current = current.right;
            } else {
                return;
            }
        }
        root = new TreeNode(val);
    }

    private void selfBalance() {

    }

    /**
     * 深度（到根节点的路径+1）
     *
     * @param node
     * @return
     */
    private int getDepth(TreeNode node) {
        TreeNode pathNode = root;
        int depth = 0;
        while (pathNode != null) {
            if (pathNode.value == node.value) {
                return ++depth;
            } else if (pathNode.value > node.value) {
                pathNode = pathNode.left;
                depth++;
            } else {
                pathNode = pathNode.right;
                depth++;
            }
        }
        return depth;
    }

    /**
     * 高度（到叶子结点的最大路径+1）
     *
     * @param node
     * @return
     */
    private int getHeight(TreeNode node, int height) {
        if (node.right == null && node.left == null) return height + 1;
        if (node.right != null && node.left != null) {
            return Math.max(getHeight(node.right, height), getHeight(node.left, height)) + 1;
        }
        if (node.right != null) return getHeight(node.right, height) + 1;
        return getHeight(node.left, height) + 1;
    }
}
