package com.bxom.coreservice.sort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Shell {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 1, 55, 2, 15, 6, 74, 2, 5, 4, 5, 8888, 25, 64};
        sort(arr);
        log.info("result: {}", arr);
    }

    private static void sort(int[] arr) {
        int size = arr.length;
        // 间隔定长截取集合进行选择排序，并通过不断缩小定长扩大筛选元素数量，对数据集进行排序
        for (int gap = size / 2; gap >= 1; gap /= 2) {
            // 从筛选后的第二项开始遍历
            for (int index = gap; index < size; index++) {
                // 插入排序
                int temp = arr[index];
                int j = index - gap;
                while (j >= 0 && temp < arr[j]) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = temp;
            }
        }
    }
}
