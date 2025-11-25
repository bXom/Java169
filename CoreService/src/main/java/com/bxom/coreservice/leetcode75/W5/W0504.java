package com.bxom.coreservice.leetcode75.W5;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class W0504 {
    /**
     * leetcode-56
     * https://leetcode.cn/problems/merge-intervals/description/
     *
     * @param args
     */
    public static void main(String[] args) {
        log.info("result: {}", merge(new int[][]{{1, 3}, {15, 18}, {8, 10}, {2, 6}}));
    }

    public static int[][] solution(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int index = 0;
        while (index < intervals.length - 1) {
            int ahead0 = intervals[index][0];
            int ahead1 = intervals[index][1];
            int behind0 = intervals[index + 1][0];
            int behind1 = intervals[index + 1][1];
            int[] newInt = new int[]{ahead0, 0};
            if (behind0 <= ahead1) {
                if (behind1 <= ahead1) {
                    newInt[1] = ahead1;
                } else {
                    newInt[1] = behind1;
                }
//                intervals[index + 1] = newInt;
//                intervals.remove[index];
            } else {
                index++;
            }
        }
        return intervals;
    }

    public static int[][] merge(int[][] intervals) {
        return solution(intervals);
    }
}
