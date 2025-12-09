package com.bxom.coreservice.leetcode75.W5;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class W0507 {
    /**
     * leetcode-721
     * https://leetcode.cn/problems/accounts-merge/description/
     *
     * @param args
     */
    public static void main(String[] args) {
        log.info("result: {}", accountsMerge(new ArrayList<>()));
    }

    public static List<List<String>> solution(List<List<String>> accounts) {
        Map<String, List<String>> resultMap = new HashMap<>();
        int length = accounts.size();
        for (int i = 0; i < length; i++) {
            List<String> account = accounts.get(i);
            int eLength = account.size();
            String name = account.get(0);
            List<String> sortedList;
            if (resultMap.containsKey(name)) {
                sortedList = resultMap.get(name);
            } else {
                sortedList = new ArrayList<>();
                int eIndex = 1;
                while (eIndex < eLength) {
                    insertSort(sortedList, account.get(eIndex));
                    eIndex++;
                }
            }
        }
        List<List<String>> result = new ArrayList<>();
        resultMap.forEach((k, v) -> {
            result.add(v);
        });
        return result;
    }

    private static void insertSort(List<String> sortedList, String e) {

    }

    /**
     * @param strA
     * @param strB
     * @return strA > strB (true：A 在 B 后)
     */
    private static boolean compare(String strA, String strB) {
        int length = Math.min(strA.length(), strB.length());
        for (int i = 0; i < length; i++) {
            if (strA.charAt(i) - strB.charAt(i) > 0) {
                return true;
            } else if (strA.charAt(i) - strB.charAt(i) < 0) {
                return false;
            }
        }
        return strA.length() > strB.length();
    }


    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        return solution(accounts);
    }
}
