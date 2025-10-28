package com.bxom.coreservice.sort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Merge {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 1, 55, 2, 15, 6, 74, 2, 5, 4, 5, 8888, 25, 64};
        sort(arr);
        log.info("result: {}", arr);
    }

    private static void sort(int[] arr) {
        int size = arr.length;
        int step = 1;
        while (2 * step < size) {
            for (int index = 0; index < size; ) {
                int[] sorted = new int[2 * step];
                int left = index;
                int right = index + step;
                log.info("step: {}, index: {}, left: {}, right: {}", step, index, left, right);
                for (int i = 0; i < 2 * step; i++) {
                    log.info("arr[left] - arr[{}]: {}", left, arr[left]);
                    log.info("arr[right] - arr[{}]: {}", right, arr[right]);
                    if (left < index + step && right < index + step * 2) {
                        if (arr[left] < arr[right]) {
                            sorted[i] = arr[left++];
                        } else {
                            sorted[i] = arr[right++];
                        }
                    } else if (left < index + step) {
                        sorted[i] = arr[left++];
                    } else if (right < index + 2 * step) {
                        sorted[i] = arr[right++];
                    }
                    log.info("sorted - i: {}, val: {}", i, sorted);
                }
                log.info("sorted: {}", sorted);
                // 覆盖原集合
                for (int i = 0; i < 2 * step; i++) {
                    arr[index + i] = sorted[i];
                }
                index += 2 * step;
            }
            step *= 2;
        }
    }
}
