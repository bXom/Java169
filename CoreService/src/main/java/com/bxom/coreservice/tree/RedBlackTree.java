package com.bxom.coreservice.tree;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedBlackTree {
    public static void main(String[] args) {
        insert(10);
        insert(5);
//        insert(20);
        insert(2);
        log.info("root - {}", root);
        log.info("root.left - {}", root.left);
        log.info("root.right - {}", root.right);
//        log.info("root.left.left - {}", root.left.left);
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

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("val: ").append(this.value)
                    .append(", isBlack: ").append(this.isBlack)
                    .append(", isLeft: ").append(this.isLeft);
            if (this.parent != null) sb.append(", parent.val: ").append(parent.value);
            else sb.append(", parent.val: null");
            if (this.left != null) sb.append(", left.val: ").append(left.value);
            else sb.append(", left.val: null");
            if (this.right != null) sb.append(", right.val: ").append(right.value);
            else sb.append(", right.val: null");
            return sb.toString();
        }
    }

    public static void insert(int val) {
        // 写入根节点
        if (root == null) {
            root = new TreeNode(val, true, -1);
            return;
        }
        TreeNode node = root;
        while (true) {
            if (node.value > val) {
                // 在左子树
                if (node.left != null) {
                    // 左子树不为空
                    node = node.left;
                } else {
                    // 左子树为空，写入
                    node.left = new TreeNode(val, 1);
                    node.left.parent = node;
                    // 检查插入节点的红黑平衡规则
                    checkIsBlack(node.left);
                    return;
                }
            } else if (node.value < val) {
                // 在右子树
                if (node.right != null) {
                    // 右子树不为空
                    node = node.right;
                } else {
                    // 右子树为空，写入
                    node.right = new TreeNode(val, 0);
                    node.right.parent = node;
                    // 检查插入节点的红黑平衡规则
                    checkIsBlack(node.right);
                    return;
                }
            }
        }
    }

    public static void delete(int val) {

    }

    /**
     * 检查插入节点的红黑平衡规则
     * 此刻的插入节点 == 红色节点
     *
     * @param node 插入节点 == 红色节点
     */
    private static void checkIsBlack(TreeNode node) {
        TreeNode parent = node.parent;
        // 根节点
        if (parent == null) {
            node.isBlack = true;
            node.isLeft = -1;
            return;
        }
        // 父节点是黑，直接退出
        if (parent.isBlack) return;
        // 插入节点为红色且父节点为红色
        TreeNode grand = parent.parent;
        TreeNode grandP = grand.parent;
        int grandIsLeft = grand.isLeft;
        TreeNode parentBeside = parent.isLeft == 1 ? grand.right : grand.left;
        if (parentBeside == null || parentBeside.isBlack) {
            TreeNode newGrand = null;
            // 叔父节点为黑色或为空
            if (parent.isLeft == 1 && node.isLeft == 1) {
                newGrand = balaLL(parent);
            } else if (parent.isLeft == 1 && node.isLeft == 0) {
                newGrand = balaLR(parent);
            } else if (parent.isLeft == 0 && node.isLeft == 1) {
                balaRL(parent);
            } else {
                balaRR(parent);
            }
            if (grandP != null) {
                if (grandIsLeft == 1) grandP.left = newGrand;
                else if (grandIsLeft == 0) grandP.right = newGrand;
            } else {
                newGrand.isLeft = -1;
                newGrand.isBlack = true;
                root = newGrand;
            }
        } else {
            // 父节点、叔父节点为红色，祖父节点为黑色，则父、叔父、祖父，颜色反向取色，再递归检查祖父节点
            parentBeside.isBlack = true;
            parent.isBlack = !parent.isBlack;
            grand.isBlack = !grand.isBlack;
            checkIsBlack(grand);
        }
    }

    private static TreeNode balaLL(TreeNode parent) {
        TreeNode grand = parent.parent;
        TreeNode grandP = grand.parent;
        parent.parent = grandP;
        parent.right = grand;

        grand.parent = parent;
        grand.left = null;
        if (grandP != null) {
            if (grand.isLeft == 1) {
                grandP.left = parent;
            } else if (grand.isLeft == 0) {
                grandP.right = parent;
            }
        }

        grand.isBlack = !grand.isBlack;
        grand.isLeft = 0;
        parent.isBlack = !parent.isBlack;
        return parent;
    }

    private static void balaRR(TreeNode parent) {
    }

    private static TreeNode balaLR(TreeNode parent) {
        TreeNode grand = parent.parent;
        TreeNode grandP = grand.parent;

        return parent;
    }

    private static void balaRL(TreeNode parent) {
    }
}
