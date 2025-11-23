package com.bxom.coreservice.leetcode75;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

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
        List<String> readXY = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (readXY.contains(i + "," + j)) {
                    continue;
                }
                boolean isIsland = grid[i][j] == '1';
                if (isIsland) {
                    fs(grid, i, j, readXY);
                    num++;
                }
            }
        }
        return num;
    }

    private static void fs(char[][] grid, int x, int y, List<String> readXY) {
        if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == '1' && !readXY.contains(x + "," + y)) {
            readXY.add(x + "," + y);
            grid[x][y] = '0';
            fs(grid, x + 1, y, readXY);
            fs(grid, x - 1, y, readXY);
            fs(grid, x, y + 1, readXY);
            fs(grid, x, y - 1, readXY);
        }
    }

    public static int numIslands(char[][] grid) {
        return solution(grid);
    }
}
