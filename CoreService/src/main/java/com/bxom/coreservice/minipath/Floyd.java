package com.bxom.coreservice.minipath;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Floyd {
    public static void main(String[] args) {
        floyd(new long[][]{
                {1, 0, 6},
                {1, 3, 2},
                {2, 1, 5},
                {2, 3, 2},
                {3, 1, 2},
                {3, 2, 1}
        });
    }

    private static final long DEFAULT = Long.MAX_VALUE / 2;

    public static void floyd(long[][] nodeMap) {
        // node 与 int 类型 id 映射表
        Map<Long, Integer> node2IdMap = new HashMap<>();
        int id = 0;
        for (long[] node : nodeMap) {
            long from = node[0];
            long to = node[1];
            if (!node2IdMap.containsKey(from)) node2IdMap.put(from, id++);
            if (!node2IdMap.containsKey(to)) node2IdMap.put(to, id++);
        }
        // 节点数量
        int nodeSize = node2IdMap.size();
        // 初始化最短距离和路径数组
        long[][] dist = new long[nodeSize][nodeSize];
        long[][] path = new long[nodeSize][nodeSize];
        // 初始化距离及路径
        for (int i = 0; i < node2IdMap.size(); i++) {
            for (int j = 0; j < node2IdMap.size(); j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = DEFAULT;
                }
                path[i][j] = -1;
            }
        }
        // 遍历节点路径，更新任意两点距离及路径
        for (long[] node : nodeMap) {
            long from = node[0];
            long to = node[1];
            long distVal = node[2];
            dist[node2IdMap.get(from)][node2IdMap.get(to)] = distVal;
            path[node2IdMap.get(from)][node2IdMap.get(to)] = from;
        }
        for (int mid = 0; mid < nodeSize; mid++) {
            for (int i = 0; i < nodeSize; i++) {
                for (int j = 0; j < nodeSize; j++) {
                    if (dist[i][mid] == DEFAULT || dist[mid][j] == DEFAULT) {
                        continue;
                    }
                    long newDistVal = dist[i][mid] + dist[mid][j];
                    if (dist[i][j] == DEFAULT || newDistVal < dist[i][j]) {
                        dist[i][j] = newDistVal;
                        path[i][j] = path[mid][j];
                    }
                }
            }
        }
    }

    private static void print(Map<Long, Integer> node2IdMap, long[][] dist, long[][] path) {
        for (Map.Entry<Long, Integer> node : node2IdMap.entrySet()) {
            log.info("节点 - ID 映射关系 ---- node: {}, id: {}", node.getKey(), node.getValue());
        }
        for (int i = 0; i < node2IdMap.size(); i++) {
            StringBuilder sbD = new StringBuilder();
            for (int j = 0; j < node2IdMap.size(); j++) {
                sbD.append(dist[i][j]).append(", ");
            }
            log.info("dist ---- i: {}, val: {}", i, sbD.substring(0, sbD.length() - 2));
        }
        for (int i = 0; i < node2IdMap.size(); i++) {
            StringBuilder sbP = new StringBuilder();
            for (int j = 0; j < node2IdMap.size(); j++) {
                sbP.append(path[i][j]).append(", ");
            }
            log.info("path ---- i: {}, val: {}", i, sbP.substring(0, sbP.length() - 2));
        }
    }
}
