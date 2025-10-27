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
        // 不断扩大排序组的元素数量
        for (int part = size / 2; part >= 1; part /= 2) {
            for (int index = part; index < size; index++) {
                int temp = arr[index];
                int j = index - part;
                while (j >= 0 && temp < arr[j]) {
                    arr[j + part] = arr[j];
                    j -= part;
                }
                arr[j + part] = temp;
            }
        }
    }
}
