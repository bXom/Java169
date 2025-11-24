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
        log.info("result: {}", search(new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3}, 1));
    }

    public static int solution(int[] nums, int target) {
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[left] == target) return left;
            if (nums[right] == target) return right;
            if (nums[mid] == target) return mid;
            if (nums[left] > nums[mid]) {
                if (target > nums[mid] && target < nums[left]) {
                    left = mid + 1;
                    right--;
                } else {
                    right = mid - 1;
                    left++;
                }
            } else {
                if (target < nums[mid] && target > nums[left]) {
                    right = mid - 1;
                    left++;
                } else {
                    left = mid + 1;
                    right--;
                }
            }
        }
        return -1;
    }

    public static int search(int[] nums, int target) {
        return solution(nums, target);
    }
}
