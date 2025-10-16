package com.bxom.coreservice.tree;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AVL {
    public static void main(String[] args) {
        AVL avl = new AVL();
        avl.insert(20);
        avl.insert(7);
        avl.insert(29);
        avl.insert(5);
        avl.insert(10);
        avl.insert(6);
//        log.info("height: {}", avl.getHeight(avl.root.right, 0));
//        log.info("depth: {}", avl.getDepth(avl.root.right));
        log.info("calHeightDistance: {}", avl.calHeightDistance(avl.root));
    }

    static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;
        public TreeNode parent;

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
                    current.left.parent = current;
                    selfBalance(current);
                    return;
                }
                current = current.left;
            } else if (current.value < val) {
                if (current.right == null) {
                    current.right = new TreeNode(val);
                    current.right.parent = current;
                    selfBalance(current);
                    return;
                }
                current = current.right;
            } else {
                return;
            }
        }
        root = new TreeNode(val);
        root.parent = null;
    }

    /**
     * 自平衡
     *
     * @param parentNode 插入节点的祖先节点
     */
    private void selfBalance(TreeNode parentNode) {
        if (parentNode == null) return;
        int pDist = calHeightDistance(parentNode);
        if (pDist > 1) {
            int subDist = calHeightDistance(parentNode.left);
            if (subDist > 0) {
                balaLL(parentNode);
            } else {
                balaLR(parentNode);
            }
        } else if (pDist < -1) {
            int subDist = calHeightDistance(parentNode.right);
            if (subDist > 0) {
                balaRL(parentNode);
            } else {
                balaRR(parentNode);
            }
        } else {
            selfBalance(parentNode.parent);
        }
    }

    /**
     * LL 类型自平衡
     *
     * @param parent 失衡节点（P 节点）
     */
    private void balaLL(TreeNode parent) {
        TreeNode sub = parent.left;
        TreeNode subRight = sub.right;
        // sub.right.parent
        if (subRight != null) subRight.parent = parent;
        // parent.left
        parent.left = subRight;
        // sub.right
        sub.right = parent;
        // pp.sub
        // sub.parent
        TreeNode parentP = parent.parent;
        sub.parent = parentP;
        if (parentP == null) {
            root = sub;
        } else if (parentP.left == parent) {
            parentP.left = sub;
        } else {
            parentP.right = sub;
        }
        // parent.parent
        parent.parent = sub;
    }

    private void balaLR(TreeNode parent) {
    }

    private void balaRR(TreeNode parent) {
        TreeNode sub = parent.right;
        TreeNode subLeft = sub.left;
        if (subLeft != null) subLeft.parent = parent;
        parent.right = subLeft;
        sub.left = parent;
        TreeNode parentP = parent.parent;
        sub.parent = parentP;
        if (parentP == null) {
            root = sub;
        } else if (parentP.right == parent) {
            parentP.right = sub;
        } else {
            parentP.left = sub;
        }
        parent.parent = sub;
    }

    private void balaRL(TreeNode parent) {
        balaR(parent.right);
        balaRR(parent);
//        TreeNode sub = parent.right;
//        TreeNode subLeft = sub.left;
//        if (subLeft.right == null) {
//            subLeft.right = sub;
//            sub.parent = subLeft;
//            sub.left = null;
//            subLeft.parent = parent;
//            parent.right = subLeft;
//            balaRR(parent);
//        } else {
//            // no.1
//            // subLeft.right
//            subLeft.right.parent = sub;
//            // sub
//            sub.left = subLeft.right;
//            sub.parent = subLeft;
//            // subLeft
//            subLeft.right = sub;
//            subLeft.parent = parent;
//            parent.right = subLeft;
//            // no.2
//            subLeft.left = parent;
//            subLeft.parent = parent.parent;
//            parent.parent = subLeft;
//        }
    }

    private void balaR(TreeNode parent) {

    }

    /**
     * 计算节点左右子树高度差
     *
     * @param node
     * @return
     */
    private int calHeightDistance(TreeNode node) {
        int left = getHeight(node.left, 0);
        int right = getHeight(node.right, 0);
        return left - right;
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
        if (node == null) return 0;
        if (node.right == null && node.left == null) return height + 1;
        if (node.right != null && node.left != null) {
            return Math.max(getHeight(node.right, height), getHeight(node.left, height)) + 1;
        }
        if (node.right != null) return getHeight(node.right, height) + 1;
        return getHeight(node.left, height) + 1;
    }
}
