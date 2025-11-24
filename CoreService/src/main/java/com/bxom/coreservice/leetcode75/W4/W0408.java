package com.bxom.coreservice.leetcode75.W4;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class W0408 {
    /**
     * leetcode-994
     * https://leetcode.cn/problems/rotting-oranges/description/
     *
     * @param args
     */
    public static void main(String[] args) {
        // 0,1,2
        log.info("result: {}", orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}));
        log.info("result: {}", orangesRotting(new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}}));
        log.info("result: {}", orangesRotting(new int[][]{{0, 2}}));
    }

    public static int solution(int[][] grid) {
        int times = 0;
        int xLength = grid.length;
        int yLength = grid[0].length;
        while (true) {
            List<String> equal2XY = new ArrayList<>();
            for (int i = 0; i < xLength; i++) {
                for (int j = 0; j < yLength; j++) {
                    if (grid[i][j] == 2) {
                        equal2XY.add(i + "," + j);
                    }
                }
            }
            if (equal2XY.isEmpty()) {
                break;
            }
            boolean change = false;
            for (String s : equal2XY) {
                int i = Integer.parseInt(s.substring(0, s.indexOf(",")));
                int j = Integer.parseInt(s.substring(s.indexOf(",") + 1));
                if (i >= 1 && grid[i - 1][j] == 1) {
                    grid[i - 1][j] = 2;
                    change = true;
                }
                if (i <= xLength - 2 && grid[i + 1][j] == 1) {
                    grid[i + 1][j] = 2;
                    change = true;
                }
                if (j >= 1 && grid[i][j - 1] == 1) {
                    grid[i][j - 1] = 2;
                    change = true;
                }
                if (j <= yLength - 2 && grid[i][j + 1] == 1) {
                    grid[i][j + 1] = 2;
                    change = true;
                }
            }
            if (!change) {
                break;
            }
            times++;
        }
        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return times;
    }

    public static int orangesRotting(int[][] grid) {
        return solution(grid);
    }
}
