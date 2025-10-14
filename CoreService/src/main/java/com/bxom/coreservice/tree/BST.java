package com.bxom.coreservice.tree;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BST {

    public static void main(String[] args) {
        BST tree = new BST();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(7);
        tree.insert(12);
        tree.insert(18);
        StringBuilder middle1 = new StringBuilder();
        tree.middleForeach(tree.root).forEach(val -> middle1.append(val).append(","));
        log.info("middle: {}", middle1);
//        StringBuilder front = new StringBuilder();
//        tree.frontForeach(tree.root).forEach(val -> front.append(val).append(","));
//        log.info("front: {}", front);
//        StringBuilder behind = new StringBuilder();
//        tree.behindForeach(tree.root).forEach(val -> behind.append(val).append(","));
//        log.info("behind: {}", behind);
        tree.delete(15);
        StringBuilder middle2 = new StringBuilder();
        tree.middleForeach(tree.root).forEach(val -> middle2.append(val).append(","));
        log.info("middle: {}", middle2);
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

    /**
     * 中序遍历
     *
     * @param root 根节点
     * @return
     */
    public List<Integer> middleForeach(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        if (root.left != null) {
            result.addAll(middleForeach(root.left));
        }
        result.add(root.value);
        if (root.right != null) {
            result.addAll(middleForeach(root.right));
        }
        return result;
    }

    /**
     * 前序遍历
     *
     * @param root 根节点
     * @return
     */
    public List<Integer> frontForeach(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        result.add(root.value);
        if (root.left != null) {
            result.addAll(frontForeach(root.left));
        }
        if (root.right != null) {
            result.addAll(frontForeach(root.right));
        }
        return result;
    }

    /**
     * 后序遍历
     *
     * @param root 根节点
     * @return
     */
    public List<Integer> behindForeach(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        if (root.left != null) {
            result.addAll(behindForeach(root.left));
        }
        if (root.right != null) {
            result.addAll(behindForeach(root.right));
        }
        result.add(root.value);
        return result;
    }

    /**
     * 查询值对应的节点
     *
     * @param val 节点值
     * @return
     */
    public TreeNode search(int val) {
        TreeNode current = root;
        while (current != null) {
            if (current.value == val) {
                return current;
            } else if (current.value > val) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    /**
     * 新增值
     *
     * @param val
     */
    public void insert(int val) {
        TreeNode current = root;
        while (current != null) {
            if (current.value > val) {
                if (current.left == null) {
                    current.left = new TreeNode(val);
                    return;
                }
                current = current.left;
            } else if (current.value < val) {
                if (current.right == null) {
                    current.right = new TreeNode(val);
                    return;
                }
                current = current.right;
            } else {
                return;
            }
        }
        root = new TreeNode(val);
    }

    public void delete(int val) {
        TreeNode current = root;
        TreeNode parent = null;
        boolean isLeft = true;
        while (current != null) {
            if (current.value < val) {
                parent = current;
                current = current.right;
                isLeft = false;
            } else if (current.value > val) {
                parent = current;
                current = current.left;
                isLeft = true;
            } else {
                if (current.left != null && current.right != null) {
                    deleteMidNodeAndReturnVal(current);
                } else if (current.left == null && current.right == null) {
                    if (parent == null) root = null;
                    else if (isLeft) parent.left = null;
                    else parent.right = null;
                } else if (current.left == null) {
                    TreeNode temp = current.right;
                    current.value = temp.value;
                    current.left = temp.left;
                    current.right = temp.right;
                } else {
                    TreeNode temp = current.left;
                    current.value = temp.value;
                    current.left = temp.left;
                    current.right = temp.right;
                }
                return;
            }
        }
    }

    private void deleteMidNodeAndReturnVal(TreeNode root) {
        setLeftSubBiggestNodeNull(root);
//        setRightSubSmallestNodeNull(root);
    }

    private void setLeftSubBiggestNodeNull(TreeNode node) {
        TreeNode parent = node;
        TreeNode sub = node.left;
        if (sub.right == null) {
            int val = sub.value;
            parent.left = sub.left;
            node.value = val;
            return;
        }
        while (true) {
            if (sub.right != null) {
                parent = sub;
                sub = sub.right;
            } else {
                int val = sub.value;
                parent.right = sub.left;
                node.value = val;
                return;
            }
        }
    }

    private void setRightSubSmallestNodeNull(TreeNode node) {
        TreeNode parent = node;
        TreeNode sub = node.right;
        if (sub.left == null) {
            int val = sub.value;
            parent.right = sub.right;
            node.value = val;
            return;
        }
        while (true) {
            if (sub.left != null) {
                parent = sub;
                sub = sub.left;
            } else {
                int val = sub.value;
                parent.left = sub.right;
                node.value = val;
                return;
            }
        }
    }
}
