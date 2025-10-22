package com.bxom.coreservice.minipath;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class LeetCode1334 {
    /**
     * leetcode-1334
     * https://leetcode.cn/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/
     * Floyd 算法
     *
     * @param args
     */
    public static void main(String[] args) {
//        findTheCity(4, new int[][]{{0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}}, 4);
        findTheCity(5, new int[][]{{0, 1, 2}, {0, 4, 8}, {1, 2, 3}, {1, 4, 2}, {2, 3, 1}, {3, 4, 1}}, 2);
    }

    public static int findTheCity(int n, int[][] edges, int distanceThreshold) {
        return solution1(n, edges, distanceThreshold);
    }

    private static final int DEFAULT = Integer.MAX_VALUE / 2;

    public static int solution1(int n, int[][] edges, int distanceThreshold) {
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = DEFAULT;
                }
            }
        }
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            int distVal = edges[i][2];
            dist[from][to] = distVal;
            dist[to][from] = distVal;
        }
        for (int mid = 0; mid < n; mid++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int newDist = dist[i][mid] + dist[mid][j];
                    if (dist[i][j] > DEFAULT || newDist < dist[i][j]) {
                        dist[i][j] = newDist;
                    }
                }
            }
        }
        int minIndex = 0;
        int minCount = n;
        for (int from = 0; from < n; from++) {
            int count = 0;
            for (int to = 0; to < n; to++) {
                if (dist[from][to] <= distanceThreshold) {
                    count++;
                }
            }
            if (count <= minCount) {
                minCount = count;
                minIndex = from;
            }
        }
        return minIndex;
    }

    private static void print(int size, int[][] dist) {
        for (int i = 0; i < size; i++) {
            StringBuilder sbD = new StringBuilder();
            for (int j = 0; j < size; j++) {
                sbD.append(dist[i][j]).append(", ");
            }
            log.info("dist ---- i: {}, val: {}", i, sbD.substring(0, sbD.length() - 2));
        }
    }
}
