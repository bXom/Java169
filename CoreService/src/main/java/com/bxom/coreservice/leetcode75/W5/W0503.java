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
        List<List<Integer>> res = new ArrayList<>();
        backtracking(nums, new boolean[nums.length], new ArrayList<>(), res);
        return res;
    }

    private static void backtracking(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> res) {
        int length = nums.length;
        if (path.size() == length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < length; i++) {
            if (used[i]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            backtracking(nums, used, path, res);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        return solution(nums);
    }
}
