package com.bxom.coreservice.tree;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedBlackTree {
    public static void main(String[] args) {
        root.insert(1);
        root.delete(1);
    }

    private static final TreeNode root = new TreeNode(0);

    static class TreeNode {
        private int value = -1;
        private boolean isBlack = true;
        private TreeNode left = null;
        private TreeNode right = null;
        private TreeNode parent;

        public TreeNode(int value) {
            this.value = value;
        }

        public TreeNode(int value, boolean isBlack) {
            this.value = value;
            this.isBlack = isBlack;
        }

        public void insert(int val) {

        }

        public void delete(int val) {

        }
    }
}
