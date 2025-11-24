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
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        while (left <= right) {
            int mid = (right - left) / 2;
            if (nums[left] == target) return left;
            if (nums[right] == target) return right;
            if (nums[mid] == target) return mid;
//            0
//            6,0,1,2,3,4,5
//            2,3,4,5,6,0,1
            if (nums[left] > nums[mid]) {
                if (target < nums[mid]) {
                    right = mid - 1;
                    left++;
                } else if (target > nums[left]) {
                    right = mid;
                    left++;
                } else {
                    left = mid + 1;
                    right--;
                }
            } else {

            }
        }
        return target;
    }

    public static int search(int[] nums, int target) {
        return solution(nums, target);
    }
}
