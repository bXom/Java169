package com.bxom.coreservice.sort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Bubble {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 1, 55, 2, 15, 6, 74, 2, 5, 4, 5, 8888, 25, 64};
        sort(arr);
        log.info("result: {}", arr);
    }

    private static void sort(int[] arr) {
        int size = arr.length;
        for (int i = 0; i < size - 1; i++) {
            int index = 1;
            while (index < size - i) {
                int temp = arr[index];
                if (arr[index - 1] > temp) {
                    arr[index] = arr[index - 1];
                    arr[index - 1] = temp;
                }
                index++;
            }
        }
    }
}
