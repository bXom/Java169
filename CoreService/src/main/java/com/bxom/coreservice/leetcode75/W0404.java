package com.bxom.coreservice.leetcode75;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class W0404 {
    /**
     * leetcode-238
     * https://leetcode.cn/problems/product-of-array-except-self/
     *
     * @param args
     */
    public static void main(String[] args) {
        log.info("result: {}", productExceptSelf(new int[]{1, 2}));
    }

    public static int[] solution(int[] nums) {
        return nums;
    }

    public static int[] productExceptSelf(int[] nums) {
        return solution(nums);
    }
}
