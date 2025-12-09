package com.bxom.coreservice.leetcode75.W5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        HashMap<String, ArrayList<HashMap<Integer, String>>> map;

        public TimeMap() {
            this.map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            map.computeIfAbsent(key, k -> new ArrayList<>());
            HashMap<Integer, String> kv = new HashMap<>();
            kv.put(timestamp, value);
            map.get(key).add(kv);
        }

        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) return "";
            ArrayList<HashMap<Integer, String>> keyMap = map.get(key);
            int start = 0;
            int end = keyMap.size() - 1;
            String result = "";
            while (start <= end) {
                int mid = start + (end - start) / 2;
                HashMap<Integer, String> map = keyMap.get(mid);
                for (Map.Entry<Integer, String> kv : map.entrySet()) {
                    int ts = kv.getKey();
                    String value = kv.getValue();
                    if (ts <= timestamp) {
                        result = value;
                        start = mid + 1;
                    } else {
                        end = mid - 1;
                    }
                }
            }
            return result;
        }
    }
}
