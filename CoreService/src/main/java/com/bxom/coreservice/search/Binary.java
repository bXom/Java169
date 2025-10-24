package com.bxom.coreservice.search;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class Binary {
    /**
     * 二分查找
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] sortedArr = sortAndRemoveRepeat(new int[]{1, 4, 55, 1, 15, 6, 74, 2, 5, 4, 5, 8888, 25, 64});
        log.info("sorted: {}", sortedArr);
        log.info("result index: {}", search(sortedArr, 1));
    }

    private static int search(int[] sortedArr, int target) {
        int left = 0;
        int right = sortedArr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (sortedArr[mid] == target) {
                return mid;
            } else if (sortedArr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    private static int[] sortAndRemoveRepeat(int[] arr) {
        if (arr.length < 2) return arr;
        Arrays.sort(arr);
        int index = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[index] == arr[i]) continue;
            arr[index + 1] = arr[i];
            index++;
        }
        return Arrays.copyOf(arr, index + 1);
    }
}
