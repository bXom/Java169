package com.bxom.coreservice.leetcode75;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class W0407 {
    /**
     * leetcode-200
     * https://leetcode.cn/problems/number-of-islands/description/
     *
     * @param args
     */
    public static void main(String[] args) {
        log.info("result: {}", numIslands(new char[][]{}));
    }

    public static int solution(char[][] grid) {
        int num = 0;
        int xLength = grid.length;
        int yLength = grid[0].length;
        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                boolean isIsland = grid[i][j] == '1';
                if (isIsland) {
                    fs(grid, i, j);
                    num++;
                }
            }
        }
        return num;
    }

    private static void fs(char[][] grid, int x, int y) {
        int xLength = grid.length;
        int yLength = grid[0].length;
        if (x >= 0 && y >= 0 && x < xLength && y < yLength && grid[x][y] == '1') {
            grid[x][y] = '0';
            fs(grid, x + 1, y);
            fs(grid, x - 1, y);
            fs(grid, x, y + 1);
            fs(grid, x, y - 1);
        }
    }

    public static int numIslands(char[][] grid) {
        return solution(grid);
    }
}
