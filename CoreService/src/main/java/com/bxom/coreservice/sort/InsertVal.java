package com.bxom.coreservice.sort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InsertVal {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 1, 55, 2, 15, 6, 74, 2, 5, 4, 5, 8888, 25, 64};
        sort(arr);
        log.info("result: {}", arr);
    }

    private static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // 缓存等待比较的目标值，从第二项开始
            int temp = arr[i];
            // 已排序的最大项
            int j = i - 1;
            // 遍历已排序的项并向右移动大于目标值的项
            while (j >= 0 && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            // 停止的项是小于目标值的后一项或全局的第0项
            arr[j + 1] = temp;
        }
    }
}
