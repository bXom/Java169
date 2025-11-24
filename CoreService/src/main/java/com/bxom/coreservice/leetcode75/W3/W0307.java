package com.bxom.coreservice.leetcode75.W3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class W0307 {
    // 试题
    // leetcode-133
    // https://leetcode.cn/problems/clone-graph/
    public static void main(String[] args) {
        cloneGraph(new Node());
    }

    public static Node solution1(Node node) {
        Map<Node, Node> viewed = new HashMap<>();
        return copy(node, viewed);
    }

    public static Node copy(Node node, Map<Node, Node> viewed){
        if (node == null) return null;
        if (viewed.containsKey(node)) {
            return viewed.get(node);
        }
        Node newNode = new Node(node.val, new ArrayList<>());
        viewed.put(node, newNode);
        node.neighbors.forEach(item -> newNode.neighbors.add(copy(item, viewed)));
        return newNode;
    }

    public static Node cloneGraph(Node node) {
        return solution1(node);
    }

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

}
