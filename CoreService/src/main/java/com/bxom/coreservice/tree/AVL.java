package com.bxom.coreservice.tree;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

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
        mForeach(root, result);
        return result;
    }

    private void mForeach(TreeNode root, List<Integer> result) {
        if (root.left != null) {
            mForeach(root.left, result);
        }
        result.add(root.value);
        if (root.right != null) {
            mForeach(root.right, result);
        }
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
        fForeach(root, result);
        return result;
    }

    private void fForeach(TreeNode root, List<Integer> result) {
        result.add(root.value);
        if (root.left != null) {
            fForeach(root.left, result);
        }
        if (root.right != null) {
            fForeach(root.right, result);
        }
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
        bForeach(root, result);
        return result;
    }

    private void bForeach(TreeNode root, List<Integer> result) {
        if (root.left != null) {
            bForeach(root.left, result);
        }
        if (root.right != null) {
            bForeach(root.right, result);
        }
        result.add(root.value);
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

    public void delete(int val) {
        TreeNode current = root;
        boolean isLeft = false;
        while (current != null) {
            if (current.value == val) {
                if (current.left == null && current.right == null) {
                    if (current.parent == null) {
                        root = null;
                        return;
                    }
                    if (isLeft) current.parent.left = null;
                    else current.parent.right = null;
                } else if (current.left != null && current.right != null) {
                    TreeNode node = removePAndReturnLeftBiggest(current);
//                    TreeNode node = removePAndReturnRightSmallest();
                    if (current.parent == null) {
                        root = node;
                        return;
                    }
                    if (isLeft) current.parent.left = node;
                    else current.parent.right = node;
                    node.parent = current.parent;
                } else if (current.left == null) {
                    if (current.parent == null) {
                        root = current.right;
                        return;
                    }
                    if (isLeft) current.parent.left = current.right;
                    else current.parent.right = current.right;
                    current.right.parent = current.parent;
                } else {
                    if (current.parent == null) {
                        root = current.left;
                        return;
                    }
                    if (isLeft) current.parent.left = current.left;
                    else current.parent.right = current.left;
                    current.left.parent = current.parent;
                }
                deleteBala(current.parent);
            } else if (current.value > val) {
                current = current.left;
                isLeft = true;
            } else {
                current = current.right;
                isLeft = false;
            }
        }
    }

    private TreeNode removePAndReturnLeftBiggest(TreeNode removeNode) {
        TreeNode sub = removeNode.left;
        while (sub.right != null) {
            sub = sub.right;
        }
        removeNode.parent.left = sub;
        sub.parent.right = removeNode.right;
        sub.parent = removeNode.parent;
        return sub;
    }
//    private TreeNode removePAndReturnRightSmallest(TreeNode node) {}

    private void deleteBala(TreeNode node) {
        while (node != null) {
            selfBalance(node);
            node = node.parent;
        }
    }

    /**
     * 自平衡
     *
     * @param parentNode 需要校验平衡因子的节点
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
        balaL(parent.left);
        balaLL(parent);
    }

    private void balaL(TreeNode sub) {
        TreeNode parent = sub.parent;
        TreeNode subRight = sub.right;
        TreeNode subRightLeft = subRight.left;
        if (subRightLeft != null) subRightLeft.parent = sub;
        sub.right = subRightLeft;
        sub.parent = subRight;
        subRight.left = sub;
        subRight.parent = parent;
        parent.left = subRight;
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
    }

    private void balaR(TreeNode sub) {
        TreeNode parent = sub.parent;
        TreeNode subLeft = sub.left;
        TreeNode subLeftRight = subLeft.right;
        if (subLeftRight != null) {
            subLeftRight.parent = sub;
        }
        sub.left = subLeftRight;
        subLeft.right = sub;
        subLeft.parent = parent;
        parent.right = subLeft;
        sub.parent = subLeft;
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
