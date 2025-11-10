package com.bxom.coreservice.tree;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedBlackTree {
    public static void main(String[] args) {
        insertTesting();
        deleteTesting();
    }

    private static void insertTesting() {
        root = null;

        // testing 2
        insert(17);
        insert(18);
        insert(23);
        insert(34);
        insert(27);
        insert(15);
        insert(9);
        insert(6);
        insert(8);
        insert(5);
        insert(25);

        // testing 1
        // insert(70);
        // insert(40);
        // insert(100);
        // insert(20);
        // insert(50);
        // insert(10);
        // insert(150);
        // insert(120);
        print("root", root);
    }

    private static void deleteTesting() {
        insert(17);
//        insert(18);
//        insert(23);
//        insert(34);
//        insert(27);
//        insert(15);
//        insert(9);
//        insert(6);
//        insert(8);
//        insert(5);
//        insert(25);
        print("root", root);
    }

    private static TreeNode root;

    static class TreeNode {
        private int value;
        private boolean isBlack = false;
        private int isLeft;
        private TreeNode left;
        private TreeNode right;
        private TreeNode parent;

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

    private static void print(String name, TreeNode node) {
        log.info("{} - {}", name, node);
        if (node.left != null) print(name + ".left", node.left);
        if (node.right != null) print(name + ".right", node.right);
    }

    public static void insert(int val) {
        // 写入根节点
        if (root == null) {
            root = new TreeNode(val, true, -1);
            return;
        }
        TreeNode node = root;
        while (true) {
            // 跳过相等值
            if (node.value == val) {
                return;
            } else if (node.value > val) {
                // 在左子树
                if (node.left != null) {
                    // 左子树不为空
                    node = node.left;
                } else {
                    // 左子树为空，写入
                    node.left = new TreeNode(val, false, 1);
                    node.left.parent = node;
                    // 检查插入节点的红黑平衡规则
                    checkIsBlack(node.left);
                    return;
                }
            } else {// node.value < val
                // 在右子树
                if (node.right != null) {
                    // 右子树不为空
                    node = node.right;
                } else {
                    // 右子树为空，写入
                    node.right = new TreeNode(val, false, 0);
                    node.right.parent = node;
                    // 检查插入节点的红黑平衡规则
                    checkIsBlack(node.right);
                    return;
                }
            }
        }
    }

    public static void delete(int val) {
        TreeNode node = root;
        while (node != null) {
            if (node.value > val) {
                node = node.left;
            } else if (node.value < val) {
                node = node.right;
            } else {
                // 无子节点
                if (node.left == null && node.right == null) {
                    TreeNode parent = node.parent;
                    // 根节点
                    if (parent == null) {
                        root = null;
                    } else {
                        // 替换 node 的值
                        if (parent.left == node) parent.left = null;
                        else parent.right = null;
                        // 当节点为黑节点，进行旋转
                        if (node.isBlack) {
                            // TODO:
                            TreeNode grand = parent.parent;
                            TreeNode bro = node.isLeft == 1 ? parent.right : parent.left;
                            checkBNodeByBro(node, parent, grand, bro);
                        }
                    }
                } else if (node.left == null) {
                    // 只有右子树 == 右子树只能是一个红色节点
                    node.right.parent = null;
                    node.value = node.right.value;
                    node.right = null;
                } else if (node.right == null) {
                    // 只有左子树 == 左子树只能是一个红色节点
                    node.left.parent = null;
                    node.value = node.left.value;
                    node.left = null;
                } else {
                    // 以查找左子树的最大值为例；也可以找右子树的最小值
                    TreeNode biggestLeftSub = node.left;
                    while (biggestLeftSub.right != null) {
                        biggestLeftSub = biggestLeftSub.right;
                    }
                    // 无子节点
                    if (biggestLeftSub.left == null) {
                        // 替换 node 的值
                        node.value = biggestLeftSub.value;
                        if (biggestLeftSub.isLeft == 1) biggestLeftSub.parent.left = null;
                        else if (biggestLeftSub.isLeft == 0) biggestLeftSub.parent.right = null;
                        // 当节点为黑节点，进行旋转
                        if (biggestLeftSub.isBlack) {
                            // TODO:
                            TreeNode biggestLeftSubParent = biggestLeftSub.parent;
                            TreeNode grand = biggestLeftSub.parent.parent;
                            TreeNode bro = biggestLeftSub.isLeft == 1 ? biggestLeftSubParent.right : biggestLeftSubParent.left;
                            checkBNodeByBro(biggestLeftSub, biggestLeftSubParent, grand, bro);
                        }
                    } else {
                        // 有子节点点且只能是红色左子节点
                        // 替换 node 的 value
                        node.value = biggestLeftSub.value;
                        // 修改被删除节点的父节点与被删除节点的左子节点的链接关系、颜色
                        TreeNode biggestP = biggestLeftSub.parent;
                        TreeNode biggestL = biggestLeftSub.left;
                        biggestP.right = biggestL;
                        biggestL.parent = biggestP;
                        biggestL.isBlack = true;
                        biggestL.isLeft = 0;
                    }
                }
            }
        }
        log.error("{} does not exist", val);
    }

    // TODO:
    private static void checkBNodeByBro(TreeNode node, TreeNode parent, TreeNode grand, TreeNode bro) {
        // 根据红黑树特性，因为 node 是黑节点，所以必有兄弟节点
        if (bro.isBlack) {
            // 根据红黑树性质永远不会存在单黑子节点
            // 双黑子节点
            if ((bro.left == null || bro.left.isBlack) && (bro.right == null || bro.right.isBlack)) {
                bro.isBlack = false;
                if (root.value == parent.value || !parent.isBlack) {
                    bro.isBlack = true;
                } else {
                    // TODO:
                    TreeNode pp = parent.parent;
                    TreeNode pg = pp == null ? null : pp.parent;
                    TreeNode ppBro = pp == null ? null : (parent.isLeft == 1 ? parent.parent.right : parent.parent.left);
                    checkBNodeByBro(parent, pp, pg, ppBro);
                }
            } else {
                // 一个红色子节点 或 两个红色子节点
                if (bro.left != null && !bro.left.isBlack) {
                    // 红色左子节点
                    if (bro.isLeft == 1) {
                        balaLLByBro(grand, parent, bro);
                    } else {
                        balaLRByBro(grand, parent, bro);
                    }
                } else {
                    // 红色右子节点
                    if (bro.isLeft == 1) {
                        balaRLByBro(grand, parent, bro);
                    } else {
                        balaRRByBro(grand, parent, bro);
                    }
                }
            }
        } else {
            // 兄弟为红色，则先将父、兄变色
            bro.isBlack = true;
            parent.isBlack = false;
            // 将父向节点旋转
            if (node.isLeft == 1) {
                // 左旋
                TreeNode broL = bro.left;
                int isLeft = parent.isLeft;
                parent.left = null;
                parent.right = broL;
                parent.parent = bro;
                parent.isLeft = 1;
                broL.parent = parent;
                broL.isLeft = 0;
                bro.left = parent;
                bro.parent = grand;
                bro.isLeft = isLeft;
                if (isLeft == -1) root = bro;
                else if (isLeft == 0) grand.right = bro;
                else grand.left = bro;
                // TODO: 此时 node 可能已经被删除，需要校验兄弟
                // checkBNodeByBro(node,);
            } else {
                // 右旋
                TreeNode broR = bro.right;
                int isLeft = parent.isLeft;
                parent.left = broR;
                parent.right = null;
                parent.parent = bro;
                parent.isLeft = 0;
                broR.parent = parent;
                ;
                broR.isLeft = 1;
                bro.right = parent;
                bro.parent = grand;
                bro.isLeft = isLeft;
                if (isLeft == -1) root = bro;
                else if (isLeft == 0) grand.right = bro;
                else grand.left = bro;
                // TODO: 此时 node 可能已经被删除，需要校验兄弟
                // checkBNodeByBro(node,);
            }
        }
    }

    private static void balaLLByBro(TreeNode grand, TreeNode parent, TreeNode bro) {
        bro.left.isBlack = bro.isBlack;
        bro.isBlack = parent.isBlack;
        parent.isBlack = true;
        TreeNode broR = bro.right;
        if (broR != null) {
            broR.parent = parent;
            broR.isLeft = 1;
        }
        parent.left = broR;
        parent.right = null;
        parent.parent = bro;
        int isLeft = parent.isLeft;
        parent.isLeft = 0;
        bro.right = parent;
        if (isLeft == -1) root = bro;
        else if (isLeft == 1) grand.left = bro;
        else grand.right = bro;
    }

    private static void balaRRByBro(TreeNode grand, TreeNode parent, TreeNode bro) {
        bro.right.isBlack = bro.isBlack;
        bro.isBlack = parent.isBlack;
        parent.isBlack = true;
        TreeNode broL = bro.left;
        if (broL != null) {
            broL.parent = parent;
            broL.isLeft = 0;
        }
        parent.parent = bro;
        parent.right = broL;
        parent.left = null;
        int isLeft = parent.isLeft;
        parent.isLeft = 1;
        bro.left = parent;
        if (isLeft == -1) root = bro;
        else if (isLeft == 1) grand.left = bro;
        else grand.right = bro;
    }

    private static void balaLRByBro(TreeNode grand, TreeNode parent, TreeNode bro) {
        int isLeft = parent.isLeft;
        TreeNode sub = bro.right;
        bro.right.isBlack = parent.isBlack;
        parent.isBlack = true;

        bro.parent = sub;
        bro.left = null;
        bro.right = parent;
        bro.isLeft = 1;
        parent.parent = sub;
        parent.left = null;
        parent.right = null;
        parent.isLeft = 0;
        sub.parent = grand;
        sub.left = bro;
        sub.right = parent;
        sub.isLeft = isLeft;
        if (isLeft == -1) root = sub;
        else if (isLeft == 1) grand.left = sub;
        else grand.right = sub;
    }

    private static void balaRLByBro(TreeNode grand, TreeNode parent, TreeNode bro) {
        int isLeft = parent.isLeft;
        TreeNode sub = bro.left;
        bro.left.isBlack = parent.isBlack;
        parent.isBlack = true;

        bro.parent = sub;
        bro.left = null;
        bro.right = null;
        bro.isLeft = 0;
        parent.parent = sub;
        parent.left = null;
        parent.right = null;
        parent.isLeft = 1;
        sub.parent = grand;
        sub.left = parent;
        sub.right = bro;
        sub.isLeft = isLeft;
        if (isLeft == -1) root = sub;
        else if (isLeft == 1) grand.left = sub;
        else grand.right = sub;
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
        TreeNode parentBeside = (parent.isLeft == 1) ? grand.right : grand.left;
        if (parentBeside == null || parentBeside.isBlack) {
            // 叔父节点为黑色或为空
            TreeNode newGrand = null;
            if (parent.isLeft == 1) {
                if (node.isLeft == 1) newGrand = balaLL(parent, grand);
                else if (node.isLeft == 0) newGrand = balaLR(parent, grand);
            } else if (parent.isLeft == 0) {
                if (node.isLeft == 1) newGrand = balaRL(parent, grand);
                else if (node.isLeft == 0) newGrand = balaRR(parent, grand);
            }
            if (grandP != null) {
                if (grandIsLeft == 1) grandP.left = newGrand;
                else if (grandIsLeft == 0) grandP.right = newGrand;
            } else {
                root = newGrand;
                newGrand.isLeft = -1;
                newGrand.isBlack = true;
            }
        } else {
            // 父节点、叔父节点为红色，祖父节点为黑色，则父、叔父、祖父，颜色反向取色，再递归检查祖父节点
            parentBeside.isBlack = true;
            parent.isBlack = true;
            grand.isBlack = false;
            checkIsBlack(grand);
        }
    }

    private static TreeNode balaLL(TreeNode parent, TreeNode grand) {
        TreeNode parentR = parent.right;
        parent.parent = grand.parent;
        parent.right = grand;
        parent.isLeft = grand.isLeft;

        grand.parent = parent;
        grand.left = parentR;
        grand.isLeft = 0;
        if (parentR != null) {
            parentR.isLeft = 1;
            parentR.parent = grand;
        }

        parent.isBlack = true;
        grand.isBlack = false;
        return parent;
    }

    private static TreeNode balaRR(TreeNode parent, TreeNode grand) {
        TreeNode parentL = parent.left;
        parent.parent = grand.parent;
        parent.left = grand;
        parent.isLeft = grand.isLeft;

        grand.parent = parent;
        grand.right = parentL;
        grand.isLeft = 1;
        if (parentL != null) {
            parentL.isLeft = 0;
            parentL.parent = grand;
        }

        parent.isBlack = true;
        grand.isBlack = false;
        return parent;
    }

    private static TreeNode balaLR(TreeNode parent, TreeNode grand) {
        TreeNode node = parent.right;
        TreeNode nodeLeft = node.left;
        TreeNode nodeRight = node.right;

        node.parent = grand.parent;
        node.left = parent;
        node.right = grand;
        node.isLeft = grand.isLeft;

        parent.parent = node;
        parent.isLeft = 1;
        parent.right = nodeLeft;
        if (nodeLeft != null) {
            nodeLeft.isLeft = 0;
            nodeLeft.parent = parent;
        }

        grand.parent = node;
        grand.isLeft = 0;
        grand.left = nodeRight;
        if (nodeRight != null) {
            nodeRight.isLeft = 1;
            nodeRight.parent = grand;
        }

        node.isBlack = true;
        grand.isBlack = false;
        return node;
    }

    private static TreeNode balaRL(TreeNode parent, TreeNode grand) {
        TreeNode node = parent.left;
        TreeNode nodeLeft = node.left;
        TreeNode nodeRight = node.right;

        node.parent = grand.parent;
        node.right = parent;
        node.left = grand;
        node.isLeft = grand.isLeft;

        parent.parent = node;
        parent.isLeft = 0;
        parent.left = nodeRight;
        if (nodeRight != null) {
            nodeRight.isLeft = 1;
            nodeRight.parent = parent;
        }

        grand.parent = node;
        grand.isLeft = 1;
        grand.right = nodeLeft;
        if (nodeLeft != null) {
            nodeLeft.isLeft = 0;
            nodeLeft.parent = grand;
        }

        grand.isBlack = false;
        node.isBlack = true;
        return node;
    }
}
