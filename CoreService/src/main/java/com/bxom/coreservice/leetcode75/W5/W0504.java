package com.bxom.coreservice.leetcode75.W5;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class W0504 {
    /**
     * leetcode-56
     * https://leetcode.cn/problems/merge-intervals/description/
     *
     * @param args
     */
    public static void main(String[] args) {
        int[][] result = merge(new int[][]{{1, 3}, {15, 18}, {8, 10}, {2, 6}});
        for (int i = 0; i < result.length; i++) {
            log.info("result: {}", result[i]);
        }
        result = merge(new int[][]{{1, 4}, {4, 6}});
        for (int i = 0; i < result.length; i++) {
            log.info("result: {}", result[i]);
        }
    }

    public static int[][] solution(int[][] intervals) {
        int length = intervals.length;
        if (length < 2) return intervals;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> resultList = new ArrayList<>();
        resultList.add(intervals[0]);
        for (int i = 1; i < length; i++) {
            int ahead1 = resultList.get(resultList.size() - 1)[1];
            int behind0 = intervals[i][0];
            int behind1 = intervals[i][1];
            if (behind0 <= ahead1) {
                if (behind1 <= ahead1) {
                    resultList.get(resultList.size() - 1)[1] = ahead1;
                } else {
                    resultList.get(resultList.size() - 1)[1] = behind1;
                }
            } else {
                resultList.add(intervals[i]);
            }
        }
        return resultList.toArray(new int[resultList.size()][]);
    }

    public static int[][] merge(int[][] intervals) {
        return solution(intervals);
    }
}
