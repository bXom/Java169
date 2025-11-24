package com.bxom.coreservice.leetcode75.W3;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class W0305 {
    // 试题
    // leetcode-15
    // https://leetcode.cn/problems/3sum/
    public static void main(String[] args) {
        threeSum(new int[]{-1, 0, 1, 2, -1, -4}).forEach(item -> {
//        threeSum(new int[]{0,0,0}).forEach(item -> {
//        threeSum(new int[]{0, 1, 1}).forEach(item -> {
            StringBuilder sb = new StringBuilder();
            item.forEach(i -> sb.append(i).append(","));
            log.info("{}", sb.substring(0, sb.length() - 1));
        });
    }

    /**
     * 双指针
     * 时间复杂度 nn
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> solution2(int[] nums) {
        Arrays.sort(nums);
        int size = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int x = i + 1;
            int y = size - 1;
            while (x < y) {
                int sum = nums[i] + nums[x] + nums[y];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[x], nums[y]));
                    x++;
                    y--;
                    while (x < y && nums[x] == nums[x - 1]) {
                        x++;
                    }
                    while (x < y && nums[y] == nums[y + 1]) {
                        y--;
                    }
                } else if (sum > 0) {
                    y--;
                } else {
                    x++;
                }
            }
        }
        return result;
    }

    /**
     * 三循环
     * 时间复杂度 nnn
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> solution1(int[] nums) {
        Arrays.sort(nums);
        int size = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            // 三个正数之和不可能等于 0
            if (nums[i] > 0) {
                break;
            }
            // 跳过相等的数
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < size; j++) {
//                log.info("i: {}, j: {}", i, j);
                // 跳过相等的数
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if (nums[i] + 2 * nums[j] > 0) {
                    break;
                }
                for (int k = j + 1; k < size; k++) {
//                    log.info("i: {}, j: {}, k: {}", i, j, k);
                    // 跳过相等的数
                    if (k > j + 1 && nums[k] == nums[k - 1]) {
                        continue;
                    }
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        log.info("push ----- i: {}, j: {}, k: {}", i, j, k);
                        result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    } else if (nums[i] + nums[j] + nums[k] > 0) {
                        break;
                    }
                }
            }
        }
        return result;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        return solution2(nums);
//        return solution1(nums);
    }
}
