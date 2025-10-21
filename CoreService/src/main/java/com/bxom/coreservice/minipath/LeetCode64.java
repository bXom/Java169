package com.bxom.coreservice.minipath;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LeetCode64 {
    /**
     * leetcode-64
     * https://leetcode.cn/problems/minimum-path-sum/
     *
     * @param args
     */
    public static void main(String[] args) {
//        log.info("{}", minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        log.info("{}", minPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }

    // 原地修改
    public static int solution2(int[][] grid) {
        int y = grid.length;
        int x = grid[0].length;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                log.info("i: {}, j:{}", i, j);
                if (j != 0 && i != 0) {
                    int dist1 = grid[i][j] + grid[i][j - 1];
                    int dist2 = grid[i][j] + grid[i - 1][j];
                    grid[i][j] = Math.min(dist1, dist2);
                } else if (j != 0) {
                    grid[i][j] = grid[i][j] + grid[i][j - 1];
                } else if (i != 0) {
                    grid[i][j] = grid[i][j] + grid[i - 1][j];
                }

            }
        }
        return grid[y - 1][x - 1];
    }

    // 空间复杂度 O(mn)
    public static int solution1(int[][] grid) {
        int y = grid.length;
        int x = grid[0].length;
        int[][] dist = new int[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                log.info("i: {}, j:{}", i, j);
                if (i == 0 && j == 0) {
                    dist[i][j] = grid[i][j];
                } else if (j != 0 && i != 0) {
                    int dist1 = grid[i][j] + dist[i][j - 1];
                    int dist2 = grid[i][j] + dist[i - 1][j];
                    dist[i][j] = Math.min(dist1, dist2);
                } else if (j != 0) {
                    dist[i][j] = grid[i][j] + dist[i][j - 1];
                } else {
                    dist[i][j] = grid[i][j] + dist[i - 1][j];
                }

            }
        }
        return dist[y - 1][x - 1];
    }

    public static int minPathSum(int[][] grid) {
        return solution2(grid);
//        return solution1(grid);
    }
}
