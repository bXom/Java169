package com.bxom.coreservice.leetcode75;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
public class W0303 {

    // 试题
    //

    /**
     * leetcode-973
     * https://leetcode.cn/problems/k-closest-points-to-origin/
     *
     * @param args
     */
    public static void main(String[] args) {
//        System.out.println("result: " + kClosest(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2));
//        18 26 20 2
        System.out.println("result: " + solution2(new int[][]{{3, 3}, {5, -1}, {-2, 4}, {1, 1}}, 2));
//        System.out.println("result: " + kClosest(new int[][]{{1, 3}, {-2, -2}}, 1));
//        System.out.println("result: " + solution2(new int[][]{{1, 3}, {-2, -2}}, 1));
    }

    /**
     * 快速排序
     *
     * @param points
     * @param k
     * @return
     */
    public static int[][] solution2(int[][] points, int k) {
        separatePartition(points, 0, points.length - 1, k - 1);
//        Arrays.stream(points).forEach(item -> log.info("{}, {}", item[0], item[1]));
        int[][] result = new int[k][];
        System.arraycopy(points, 0, result, 0, k);
        return result;
    }

    public static void separatePartition(int[][] points, int left, int right, int target) {
        int randomIndex = partition(points, left, right);
//        log.info("left: {}, right: {}, middle: {}", left, right, randomIndex);
        if (randomIndex == target) return;
        if (randomIndex < target) {
            separatePartition(points, randomIndex, right, target);
        } else {
            separatePartition(points, left, randomIndex - 1, target);
        }
    }

    public static int partition(int[][] points, int left, int right) {
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (calVal(points[j]) <= calVal(points[right])) {
                i++;
                int[] temp = points[i];
                points[i] = points[j];
                points[j] = temp;
            }
        }
        i++;
        int[] temp = points[i];
        points[i] = points[right];
        points[right] = temp;
        return i;
    }

    public static int calVal(int[] item) {
        return item[0] * item[0] + item[1] * item[1];
    }

    public static int[][] kClosest(int[][] points, int k) {
        List<int[]> sorted = Arrays
                .stream(points)
                .sorted(Comparator.comparingInt(item -> calVal(item))).toList();
        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            int[] item = sorted.get(i);
            result[i] = item;
        }
        return result;
    }
}
