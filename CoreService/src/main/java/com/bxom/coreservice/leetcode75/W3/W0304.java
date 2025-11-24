package com.bxom.coreservice.leetcode75.W3;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class W0304 {
    // 试题
    // leetcode-3
    // https://leetcode.cn/problems/longest-substring-without-repeating-characters/
    public static void main(String[] args) {
        log.info("{}", lengthOfLongestSubstring("abcabcbb"));
        log.info("{}", lengthOfLongestSubstring("bbbbb"));
        log.info("{}", lengthOfLongestSubstring("pw"));
        log.info("{}", lengthOfLongestSubstring(""));
        log.info("{}", lengthOfLongestSubstring("1441"));
    }

    public static int solution1(String s) {
        int start = 0;
        int maxLength = 0;
        Map<Object, Integer> map = new HashMap<>();
        int point = 0;
        while (point < s.length()) {
            char str = s.charAt(point);
            if (map.containsKey(str) && map.get(str) >= start) {
                start = map.get(str) + 1;
            }
            map.put(str, point);
            maxLength = Math.max(maxLength, point - start + 1);
            point++;
        }
        return maxLength;
    }

    public static int lengthOfLongestSubstring(String s) {
        return solution1(s);
    }
}
