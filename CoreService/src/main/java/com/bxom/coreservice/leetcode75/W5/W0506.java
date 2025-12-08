package com.bxom.coreservice.leetcode75.W5;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class W0506 {
    /**
     * leetcode-981
     * https://leetcode.cn/problems/time-based-key-value-store/
     *
     * @param args
     */
    public static void main(String[] args) {
    }

    class TimeMap {
        HashMap<String, TreeMap<Integer, String>> map;

        public TimeMap() {
            this.map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            if (!map.containsKey(key)) {
                map.put(key, new TreeMap<>());
            }
            map.get(key).put(timestamp, value);
        }

        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) return "";
            Map<Integer, String> keyMap = map.get(key);
            if (keyMap.containsKey(timestamp)) return keyMap.get(timestamp);
            int start = 1;
            int end = timestamp - 1;
            while (start <= end) {
                int mid = start + (end - start) / 2;
                if (keyMap.containsKey(mid)) start = mid + 1;
                else end = mid - 1;
            }
            return "";
        }
    }
}
