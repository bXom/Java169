package com.bxom.coreservice.sort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Heap {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 1, 55, 2, 15, 6, 74, 2, 5, 4, 5, 8888, 25, 64};
//        int[] arr = new int[]{30, 27, 18, 48, 60, 7, 51, 19, 48, 53};
        sort(arr);
        log.info("result: {}", arr);
    }

    private static void sort(int[] arr) {
        int size = arr.length;
        // 转换为大根堆
        for (int root = size / 2 - 1; root >= 0; root--) {
            parse2HeapFromTop(arr, root, size - 1);
        }
//        log.info("----------------------------------------------");
        for (int i = 0; i < size - 1; i++) {
            exchangeVal(arr, 0, size - 1 - i);
            parse2HeapFromTop(arr, 0, size - i - 2);
        }
    }

    private static void parse2HeapFromTop(int[] arr, int root, int board) {
        // (root+1)*2-1
        int left = 2 * root + 1;
        // (root+1)*2+1-1
        int right = 2 * root + 2;
        if (left <= board && right <= board) {
            // 有左右子节点
            int maxIndex = root;
            if (arr[left] > arr[maxIndex]) maxIndex = left;
            if (arr[right] > arr[maxIndex]) maxIndex = right;
            if (maxIndex != root) {
                exchangeVal(arr, maxIndex, root);
                parse2HeapFromTop(arr, maxIndex, board);
            }
        } else if (left <= board && arr[left] > arr[root]) {
            // 有左无右子节点
            exchangeVal(arr, left, root);
            parse2HeapFromTop(arr, left, board);
        }
    }

    private static void exchangeVal(int[] arr, int i, int j) {
//        log.info("i -- arr[{}]: {}", i, arr[i]);
//        log.info("j -- arr[{}]: {}", j, arr[j]);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
//        log.info("after exchange: {}", arr);
    }
}
