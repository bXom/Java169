package com.bxom.coreservice.sort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Merge {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 1, 55, 2, 15, 6, 74, 2, 5, 4, 5, 8888, 25, 64};
//        int[] arr = new int[]{3, 1, 2, 4};
        sort(arr);
        log.info("result: {}", arr);
    }

    private static void sort(int[] arr) {
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
}
