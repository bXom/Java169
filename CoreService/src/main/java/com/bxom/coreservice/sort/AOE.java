package com.bxom.coreservice.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class AOE {
    public static void main(String[] args) {
        log.info("maxPath length: {}", calMaxPath(new int[][]{
                {1, 2, 2},
                {1, 3, 5},
                {2, 4, 3},
                {2, 3, 1},
                {3, 4, 3},
                {3, 5, 4},
                {3, 6, 1},
                {4, 5, 1},
                {4, 6, 4},
                {5, 6, 1},
        }));
    }

    public static int calMaxPath(int[][] graph) {
        // 事件出度入度，int[0] = 出度, int[1] = 入度
        Map<Integer, int[]> oiMap = new HashMap<>();
        for (int[] map : graph) {
            oiMap.merge(map[0], new int[]{1, 0}, (act, defaultAct) -> {
                act[0] += 1;
                return act;
            });
            oiMap.merge(map[1], new int[]{0, 1}, (act, defaultAct) -> {
                act[1] += 1;
                return act;
            });
        }
        // 图的二维数组表达形式
        int[][] graph2Matrix = new int[oiMap.size()][oiMap.size()];
        for (int i = 0; i < oiMap.size(); i++) {
            Arrays.fill(graph2Matrix[i], -1);
            graph2Matrix[i][i] = 0;
        }
        for (int[] activity : graph) {
            graph2Matrix[activity[0] - 1][activity[1] - 1] = activity[2];
        }
//        printMatrix(graph2Matrix);
//        oiMap.forEach((k, v) -> log.info("{}: {}, {}", k, v[0], v[1]));
        Map<Integer, Integer> minMap = new HashMap<>();
        calMinEvent(graph2Matrix, minMap, oiMap);
//        minMap.forEach((k, v) -> log.info("min ---- {}: {}", k, v));
        Map<Integer, Integer> maxMap = new HashMap<>();
//        oiMap.forEach((k, v) -> log.info("{}: {}, {}", k, v[0], v[1]));
        calMaxEvent(graph2Matrix, maxMap, oiMap, minMap);
//        maxMap.forEach((k, v) -> log.info("max ---- {}: {}", k, v));
        calActivity(graph2Matrix, minMap, maxMap);
        return 1;
    }

    private static void calMinEvent(int[][] graph2Matrix, Map<Integer, Integer> minMap, Map<Integer, int[]> oiMap) {
        int times = 0;
        // 总遍历次数等于事件数量
        while (times < graph2Matrix.length) {
            for (Map.Entry<Integer, int[]> item : oiMap.entrySet()) {
                int[] oi = item.getValue();
                // 依次根据入度最小的遍历事件
                if (oi[1] == 0) {
                    if (times == 0) minMap.put(item.getKey(), 0);
                    times++;
                    // matrix from index
                    int from = item.getKey() - 1;
                    // matrix to index
                    int[] toList = graph2Matrix[from];
                    for (int to = 0; to < toList.length; to++) {
                        if (graph2Matrix[from][to] < 1) continue;
                        if (!minMap.containsKey(to + 1)) minMap.put(to + 1, 0);
                        int newVal = minMap.get(from + 1) + graph2Matrix[from][to];
                        minMap.put(to + 1, Math.max(newVal, minMap.get(to + 1)));
                        oiMap.get(to + 1)[1] -= 1;
                    }
                }
            }
        }
    }

    private static void calMaxEvent(int[][] graph2Matrix, Map<Integer, Integer> maxMap, Map<Integer, int[]> oiMap, Map<Integer, Integer> minMap) {
        int maxPathVal = 0;
        for (Map.Entry<Integer, Integer> item : minMap.entrySet()) {
            maxPathVal = Math.max(item.getValue(), maxPathVal);
        }
        int times = 0;
        // 总遍历次数等于事件数量
        while (times < graph2Matrix.length) {
            for (Map.Entry<Integer, int[]> item : oiMap.entrySet()) {
                int[] oi = item.getValue();
                // 依次根据出度最小的遍历事件
                if (oi[0] == 0) {
                    if (times == 0) maxMap.put(item.getKey(), maxPathVal);
                    times++;
                    int to = item.getKey() - 1;
                    for (int from = 0; from < graph2Matrix.length; from++) {
                        if (graph2Matrix[from][to] < 1) continue;
                        if (!maxMap.containsKey(from + 1)) maxMap.put(from + 1, maxPathVal);
                        int newVal = maxMap.get(to + 1) - graph2Matrix[from][to];
                        maxMap.put(from + 1, Math.min(newVal, maxMap.get(from + 1)));
                        oiMap.get(from + 1)[0] -= 1;
                        oiMap.get(to + 1)[0] -= 1;
                    }
                }
            }
        }
    }

    private static void calActivity(int[][] graph2Matrix, Map<Integer, Integer> minMap, Map<Integer, Integer> maxMap) {
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < matrix[i].length; j++) {
                sb.append(matrix[i][j]).append(",");
            }
            log.info("i: {}, val: {}", i, sb.substring(0, sb.length() - 1));
        }
    }
}
