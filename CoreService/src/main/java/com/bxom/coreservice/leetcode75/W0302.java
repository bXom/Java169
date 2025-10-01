package com.bxom.coreservice.leetcode75;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class W0302 {
    /**
     * leetcode-542
     * https://leetcode.cn/problems/01-matrix/
     *
     * @param args
     */
    public static void main(String[] args) {
        int[][] mat = updateMatrix(new int[][]{{1, 0, 1, 1, 0, 0, 1, 0, 0, 1}, {0, 1, 1, 0, 1, 0, 1, 0, 1, 1}, {0, 0, 1, 0, 1, 0, 0, 1, 0, 0}, {1, 0, 1, 0, 1, 1, 1, 1, 1, 1}, {0, 1, 0, 1, 1, 0, 0, 0, 0, 1}, {0, 0, 1, 0, 1, 1, 1, 0, 1, 0}, {0, 1, 0, 1, 0, 1, 0, 0, 1, 1}, {1, 0, 0, 0, 1, 1, 1, 1, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 0, 1, 0}, {1, 1, 1, 1, 0, 1, 0, 0, 1, 1}});
    }

    /**
     * 思路：
     * 1. 首先将所有0的位置标记为 0，所有 1 的位置标记为 -1
     * 2. 然后从 0 开始，逐层向外扩展，修改 -1 为 1/2/3/4 ......
     *
     * @param mat
     * @return
     */
    public static int[][] updateMatrix(int[][] mat) {
        int height = mat.length;
        int width = mat[0].length;
        int target = 0;
        int maxVal = 1;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (mat[y][x] == 1) {
                    mat[y][x] = -1;
                } else {
                    mat[y][x] = 0;
                }
            }
        }
        printMatrix(mat);
        do {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (mat[y][x] == target) {
                        maxVal = Math.max(maxVal, refreshTargetVal(target, mat, x, y, height, width));
                    }
                }
            }
        } while (maxVal != target++);
        return mat;
    }

    public static int refreshTargetVal(int target, int[][] mat, int x, int y, int height, int width) {
        boolean isRefresh = false;
        if (x - 1 >= 0 && (mat[y][x - 1] == -1 || mat[y][x - 1] >= target + 1)) {
            mat[y][x - 1] = target + 1;
            isRefresh = true;
        }
        if (x + 1 < width && (mat[y][x + 1] == -1 || mat[y][x + 1] >= target + 1)) {
            mat[y][x + 1] = target + 1;
            isRefresh = true;
        }
        if (y - 1 >= 0 && (mat[y - 1][x] == -1 || mat[y - 1][x] >= target + 1)) {
            mat[y - 1][x] = target + 1;
            isRefresh = true;
        }
        if (y + 1 < height && (mat[y + 1][x] == -1 || mat[y + 1][x] >= target + 1)) {
            mat[y + 1][x] = target + 1;
            isRefresh = true;
        }
        log.info("x: {}, y: {}, target: {}, isRefresh: {}", x, y, target, isRefresh);
        printMatrix(mat);
        if (isRefresh) return target + 1;
        return target;
    }

    public static String printMatrix(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < mat[i].length; j++) {
                sb.append(mat[i][j]).append(",");
            }
            log.info("{} ", sb.substring(0, sb.length() - 1));
        }
        return "";
    }
}