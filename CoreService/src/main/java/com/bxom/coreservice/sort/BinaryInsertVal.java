package com.bxom.coreservice.sort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BinaryInsertVal {
    public static void main(String[] args) {
//        int[] arr = new int[]{4, 1, 55, 2};
//        int[] arr = new int[]{4, 1, 55, 2, 15, 6};
        int[] arr = new int[]{4, 1, 55, 2, 15, 6, 74, 2, 5, 4, 5, 8888, 25, 64};
        sort(arr);
        log.info("result: {}", arr);
    }

    private static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] >= arr[i - 1]) continue;
            int temp = arr[i];
            int left = 0;
            int right = i - 1;
            // 通过二分查找定位
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (temp > arr[mid]) left = mid + 1;
                else right = mid - 1;
            }
            for (int j = i - 1; j >= left; j--) {
                arr[j + 1] = arr[j];
            }
            arr[left] = temp;
        }
    }
}
