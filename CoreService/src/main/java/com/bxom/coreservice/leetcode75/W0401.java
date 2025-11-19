package com.bxom.coreservice.leetcode75;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class W0401 {
    // 试题
    // leetcode-207
    // https://leetcode.cn/problems/course-schedule/
    public static void main(String[] args) {
        log.info("result: {}", canFinish(2, new int[][]{{1, 0}, {0, 1}}));
    }

    // TODO: failed
    private static boolean solution1(int numCourses, int[][] prerequisites) {
        Map<Integer, Integer> intoMap = new HashMap<>();
        for (int[] item : prerequisites) {
            intoMap.put(item[0], intoMap.getOrDefault(item[0], 0) + 1);
            intoMap.put(item[1], intoMap.getOrDefault(item[1], 0));
        }
        List<Integer> sortedList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> item : intoMap.entrySet()) {
            int times = item.getValue();
            int name = item.getKey();
            if (times == 0) {

            }
        }
        return false;
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        return solution1(numCourses, prerequisites);
    }
}
