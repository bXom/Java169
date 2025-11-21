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
        log.info("result: {}", productExceptSelf(new int[]{1, 2, 3, 4, 5}));
    }

    public static int[] solution2(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            result[i] = nums[i - 1] * result[i - 1];
        }
        int rightVal = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] *= rightVal;
            rightVal *= nums[i];
        }
        return result;
    }

    public static int[] solution(int[] nums) {
        int[] left = new int[nums.length];
        left[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            left[i] = nums[i - 1] * left[i - 1];
        }
        int[] right = new int[nums.length];
        right[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            right[i] = nums[i + 1] * right[i + 1];
        }
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = left[i] * right[i];
        }
        return result;
    }

    public static int[] productExceptSelf(int[] nums) {
        return solution2(nums);
//        return solution(nums);
    }
}
