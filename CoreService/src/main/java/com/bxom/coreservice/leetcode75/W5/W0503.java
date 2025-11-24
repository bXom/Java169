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
        boolean[] used = new boolean[nums.length];
        backtrack(nums, new ArrayList<>(), used, res);
        return res;
    }

    private static void backtrack(int[] nums, List<Integer> path, boolean[] used, List<List<Integer>> res) {
        // 结束条件
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path)); // 必须new一个新的ArrayList
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            // 做选择
            path.add(nums[i]);
            used[i] = true;

            // 递归
            backtrack(nums, path, used, res);

            // 撤销选择
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        return solution(nums);
    }
}
