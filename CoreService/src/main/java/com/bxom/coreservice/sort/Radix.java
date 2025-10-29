package com.bxom.coreservice.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

@Slf4j
public class Radix {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 1, 55, 2, 17, 6, 74, 2, 53, 4, 5, 8888, 25, 64};
        sort(arr);
        log.info("result: {}", arr);
    }

    private static void sort(int[] arr) {
        int size = arr.length;
        Map<Integer, Queue<Integer>> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(i, new LinkedList<>());
        }
        int base = 1;
        int max = Integer.MIN_VALUE;
        for (int value : arr) {
            if (value > max) {
                max = value;
            }
        }
        while (base / 10 <= max) {
            for (int item : arr) {
                int num = item / base % 10;
                map.get(num).add(item);
            }
            Queue<Integer> result = new LinkedList<>();
            for (int i = 0; i < 10; i++) {
                Queue<Integer> vList = map.get(i);
                while (!vList.isEmpty()) {
                    result.add(vList.poll());
                }
            }
            for (int i = 0; i < size; i++) {
                arr[i] = result.poll();
            }
            base *= 10;
        }
    }
}
