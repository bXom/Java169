package com.bxom.coreservice.sort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Merge {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 1, 55, 2, 15, 6, 74, 2, 5, 4, 5, 8888, 25, 64};
//        int[] arr = new int[]{3, 1, 2, 4};
//        sort1(arr);
        sort2(arr, 0, arr.length);
        log.info("result: {}", arr);
    }

    private static void sort1(int[] arr) {
        int size = arr.length;
        // 待归并的两个集合元素步长,初始值为 1,依次翻倍
        int step = 1;
        while (size > step) {
            // 单次归并的两个集合的指针
            for (int index = 0; index < size; ) {
                // 子集合指针
                int left = index;
                int right = index + step;
                int leftEnd = Math.min(index + step, size);
                int rightEnd = Math.min(index + step * 2, size);
                // 归并后得到的集合
                int subSize = rightEnd - index;
                int[] sorted = new int[subSize];
                for (int i = 0; i < subSize; i++) {
                    if (left < leftEnd && right < rightEnd) {
                        if (arr[left] < arr[right]) {
                            sorted[i] = arr[left++];
                        } else {
                            sorted[i] = arr[right++];
                        }
                    } else if (left < leftEnd) {
                        sorted[i] = arr[left++];
                    } else if (right < rightEnd) {
                        sorted[i] = arr[right++];
                    }
                }
                // 覆盖原集合
                for (int i = 0; i < subSize; i++) {
                    arr[index + i] = sorted[i];
                }
                index += 2 * step;
            }
            step *= 2;
        }
    }

    // 递归
    private static void sort2(int[] arr, int start, int end) {
        if (end - start < 2) {
            return;
        }
        int mid = start + (end - start) / 2;
        sort2(arr, start, mid);
        sort2(arr, mid, end);
        merge(arr, start, mid, end);
    }

    private static void merge(int[] arr, int start, int mid, int end) {
        int left = start;
        int right = mid;
        int[] sorted = new int[end - start];
        for (int i = start; i < end; i++) {
            if (left < mid && right < end) {
                if (arr[left] < arr[right]) sorted[i - start] = arr[left++];
                else sorted[i - start] = arr[right++];
            } else if (left < mid) {
                sorted[i - start] = arr[left++];
            } else if (right < end) {
                sorted[i - start] = arr[right++];
            }
        }
        for (int i = start; i < end; i++) {
            arr[i] = sorted[i - start];
        }
    }
}
