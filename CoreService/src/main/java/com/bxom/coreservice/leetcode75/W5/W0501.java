package com.bxom.coreservice.leetcode75.W5;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class W0501 {
    /**
     * leetcode-33
     * https://leetcode.cn/problems/search-in-rotated-sorted-array/description/
     *
     * @param args
     */
    public static void main(String[] args) {
        log.info("result: {}", search(new int[]{3, 5, 92, 1, 952, 34}, 1));
    }

    public static int solution(int[] nums, int target) {
        return target;
    }

    public static int search(int[] nums, int target) {
        return solution(nums, target);
    }
}
