package com.bxom.coreservice.leetcode75.W5;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class W0502 {
    /**
     * leetcode-39
     * https://leetcode.cn/problems/combination-sum/description/
     *
     * @param args
     */
    public static void main(String[] args) {
        log.info("result: {}", combinationSum(new int[]{2, 3, 6, 7}, 7));
    }

    public static List<List<Integer>> solution(int[] candidates, int target) {
        Map<Integer, List<List<Integer>>> sumMap = new HashMap<>();
        sumMap.put(0, new ArrayList<>());
        for (int sum = 1; sum <= target; sum++) {
            int length = candidates.length;
            for (int i = 0; i < length; i++) {
                if (candidates[i] == sum) {
                    List<List<Integer>> sumList = sumMap.getOrDefault(sum, new ArrayList<>());
                    sumList.add(List.of(candidates[i]));
                    sumMap.put(sum, sumList);
                } else if (candidates[i] < sum) {
                    List<List<Integer>> aheadList = sumMap.get(sum - candidates[i]);
                    if (aheadList != null && !aheadList.isEmpty()) {
                        for (List<Integer> item : aheadList) {
                            List<Integer> newList = new ArrayList<>(item);
                            newList.add(candidates[i]);
                            newList.sort(Integer::compareTo);
                            List<List<Integer>> sumList = sumMap.getOrDefault(sum, new ArrayList<>());
                            // 遍历历史记录，查询是否有相同结果
                            boolean repeat = false;
                            for (List<Integer> integers : sumList) {
                                int size = integers.size();
                                for (int index = 0; index < size; index++) {
                                    // 出现不相等的则直接跳出循环
                                    if (!integers.get(index).equals(newList.get(index))) {
                                        break;
                                    }
                                    // 所有数字均相等，则判定重复
                                    if (index == size - 1) {
                                        repeat = true;
                                    }
                                }
                                // 存在相等则跳出循环
                                if (repeat) break;
                            }
                            // 存在相等，则跳过
                            if (repeat) {
                                continue;
                            }
                            // 不存在相等，则新增结果
                            sumList.add(newList);
                            sumMap.put(sum, sumList);
                        }
                    }
                }
            }
        }
//        log.info("------------------sumMap------------------");
//        sumMap.forEach((k, list) -> {
//            log.info("sum: {}", k);
//            list.forEach(item -> {
//                StringBuilder sb = new StringBuilder();
//                item.forEach(i -> sb.append(i).append(","));
//                log.info("{}", sb.substring(0, sb.length() - 1));
//            });
//        });
        if (!sumMap.containsKey(target) || sumMap.get(target) == null || sumMap.get(target).isEmpty()) {
            return new ArrayList<>();
        }
        return sumMap.get(target);
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        return solution(candidates, target);
    }
}
