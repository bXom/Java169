package com.bxom.coreservice.leetcode75.W5;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class W0503 {
    /**
     * leetcode-46
     * https://leetcode.cn/problems/permutations/description/
     *
     * @param args
     */
    public static void main(String[] args) {
        log.info("result: {}", permute(new int[]{1, 2, 5}));
    }

    public static List<List<Integer>> solution(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        return result;
    }

    public static List<List<Integer>> permute(int[] nums) {
        return solution(nums);
    }
}
