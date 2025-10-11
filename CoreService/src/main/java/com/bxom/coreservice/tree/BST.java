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
        tree.delete(10);
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

    // TODO:
    public void insideForeach() {
        List<Integer> list = new ArrayList<>();
        TreeNode current = root;
        while (current != null) {
            if (current.left != null) {
                current = current.left;
            } else if (current.right != null) {
                current = current.right;
            } else {
                list.add(current.value);
            }
        }
    }

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
        while (current != null) {
            if (current.value < val) {
                current = current.right;
            } else if (current.value > val) {
                current = current.left;
            } else {
                if (current.left != null && current.right != null) {
                    deleteMidNode();
                } else if (current.left == null && current.right == null) {
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

    // TODO:
    private void deleteMidNode() {
//        searchLeftBiggestNode();
//        searchRightSmallestNode();
    }
}
