package com.bxom.coreservice.leetcode75.W4;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class W0401 {
    // 试题
    // leetcode-207
    // https://leetcode.cn/problems/course-schedule/
    public static void main(String[] args) {
        log.info("result: {}", canFinish(2, new int[][]{{1, 0}}));
    }

    private static boolean solution1(int numCourses, int[][] prerequisites) {
        // 统计节点的入度
        Map<Integer, Integer> intoMap = new HashMap<>();
        for (int[] item : prerequisites) {
            intoMap.put(item[0], intoMap.getOrDefault(item[0], 0) + 1);
            intoMap.put(item[1], intoMap.getOrDefault(item[1], 0));
        }
        if (intoMap.size() > numCourses) return false;
        List<Integer> sortedList = new ArrayList<>();
        while (!intoMap.isEmpty()) {
            // 遍历入度为 0 的节点
            int name = -1;
            for (Map.Entry<Integer, Integer> item : intoMap.entrySet()) {
                if (item.getValue() == 0) {
                    name = item.getKey();
                    intoMap.remove(name);
                    sortedList.add(name);
                    break;
                }
            }
            // 不存在入度为 0 的节点
            if (name == -1) return false;
            // 为以 name 为前提的节点的入度减 1
            for (int[] item : prerequisites) {
                if (item[1] == name) {
                    intoMap.put(item[0], intoMap.get(item[0]) - 1);
                }
            }
        }
        sortedList.forEach(i -> log.info("item: {}", i));
        return true;
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        return solution1(numCourses, prerequisites);
    }
}
