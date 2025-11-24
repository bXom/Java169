package com.bxom.coreservice.leetcode75.W5;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

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
        List<List<Integer>> result = new ArrayList<>();
        return result;
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        return solution(candidates, target);
    }
}
