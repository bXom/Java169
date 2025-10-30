package com.bxom.coreservice.tree;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedBlackTree {
    public static void main(String[] args) {
        insert(10);
        insert(5);
//        insert(20);
        insert(2);
        log.info("root - val: {}, isBlack: {}, isLeft: {}", root.value, root.isBlack, root.isLeft);
        log.info("root.left - val: {}, isBlack: {}, isLeft: {}", root.left.value, root.left.isBlack, root.left.isLeft);
//        log.info("root.right - val: {}, isBlack: {}, isLeft: {}", root.right.value, root.right.isBlack, root.right.isLeft);
        log.info("root.left.left - val: {}, isBlack: {}, isLeft: {}", root.left.left.value, root.left.left.isBlack, root.left.left.isLeft);
//        delete(1);
    }

    private static TreeNode root;

    static class TreeNode {
        private int value;
        private boolean isBlack = false;
        private int isLeft;
        private TreeNode left;
        private TreeNode right;
        private TreeNode parent;

        public TreeNode(int value, int isLeft) {
            this.value = value;
            this.isLeft = isLeft;
        }

        public TreeNode(int value, boolean isBlack, int isLeft) {
            this.value = value;
            this.isBlack = isBlack;
            this.isLeft = isLeft;
        }
    }

    public static void insert(int val) {
        if (root == null) {
            root = new TreeNode(val, true, -1);
            return;
        }
        TreeNode node = root;
        while (true) {
            if (node.value > val) {
                if (node.left != null) {
                    node = node.left;
                } else {
                    node.left = new TreeNode(val, 1);
                    node.left.parent = node;
                    checkIsBlack(node.left);
                    return;
                }
            } else if (node.value < val) {
                if (node.right != null) {
                    node = node.right;
                } else {
                    node.right = new TreeNode(val, 0);
                    node.right.parent = node;
                    checkIsBlack(node.right);
                    return;
                }
            }
        }
    }

    public static void delete(int val) {

    }

    private static void checkIsBlack(TreeNode node) {
        log.info("node.val: {}, isleft: {}, isblack: {}", node.value, node.isLeft, node.isBlack);
        TreeNode parent = node.parent;
        if (parent == null) {
            node.isBlack = true;
            return;
        }
        if (parent.isBlack) return;
        // 插入节点为红色且父节点为红色
        TreeNode grand = parent.parent;
        TreeNode parentBeside = parent.isLeft == 1 ? grand.right : grand.left;
        if (parentBeside == null || parentBeside.isBlack) {
            TreeNode newGrand = balaLL(parent);
            if (grand.parent == null) root = newGrand;
            else if (grand.isLeft == 1) grand.parent.left = newGrand;
            else grand.parent.right = newGrand;
        } else {
            // 父节点的兄弟节点也为红色，则父、叔、祖父，颜色倒置，再递归检查祖父节点
            parentBeside.isBlack = true;
            parent.isBlack = true;
            grand.isBlack = false;
            checkIsBlack(grand);
        }
    }

    private static TreeNode balaLL(TreeNode parent) {
        TreeNode grand = parent.parent;
        parent.parent = grand.parent;
        parent.right = grand;

        grand.parent = parent;
        grand.left = null;

        grand.isBlack = !grand.isBlack;
        parent.isBlack = !parent.isBlack;
        return grand;
    }
}
