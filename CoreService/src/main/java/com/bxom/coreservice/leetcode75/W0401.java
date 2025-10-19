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

    private static boolean solution1(int numCourses, int[][] prerequisites) {
//        if (prerequisites.length == 0) return true;
        Map<Integer, Set<Integer>> preMap = new HashMap<>();
        for (int[] cond : prerequisites) {
//            if (cond[0] == cond[1]) return false;
            if (!preMap.containsKey(cond[0])) {
                preMap.put(cond[0], new HashSet<>());
            }
            if (!preMap.containsKey(cond[1])) {
                preMap.put(cond[1], new HashSet<>());
            }
            preMap.get(cond[0]).add(cond[1]);
            Queue<Integer> checkQueue = new LinkedList<>();
            checkQueue.add(cond[0]);
            if (!reloadPre(preMap, checkQueue)) return false;
//            preMap.forEach((k, vList) -> {
//                StringBuilder sb = new StringBuilder();
//                vList.forEach(v -> sb.append(v).append(","));
//                log.info("{}: {}", k, sb);
//            });
//            log.info("----------------");
        }
        return preMap.size() <= numCourses;
    }

    private static boolean reloadPre(Map<Integer, Set<Integer>> preMap, Queue<Integer> checkQueue) {
        while (!checkQueue.isEmpty()) {
            Integer key = checkQueue.poll();
//            log.info("key: {}, remainSIze: {}", key, checkQueue.size());
            preMap.forEach((k, vList) -> {
                Set<Integer> newSet = new HashSet<>();
                vList.forEach(v -> {
                    if (v.equals(key)) {
                        newSet.addAll(preMap.get(key));
                        if (newSet.contains(k)) return;
                        checkQueue.add(k);
                    }
                });
                preMap.get(k).addAll(newSet);
            });
        }
        for (Map.Entry<Integer, Set<Integer>> entry : preMap.entrySet()) {
            if (entry.getValue().contains(entry.getKey())) return false;
        }
        return true;
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        return solution1(numCourses, prerequisites);
    }
}
