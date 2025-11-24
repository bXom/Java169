package com.bxom.coreservice.leetcode75.W4;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class W0403 {
    /**
     * leetcode-322
     * https://leetcode.cn/problems/coin-change/
     *
     * @param args
     */
    public static void main(String[] args) {
        log.info("{}", coinChange(new int[]{1, 2, 5}, 11));
    }

    public static int solution(int[] coins, int amount) {
        Arrays.sort(coins);
        // 金额从 0 到 amount
        int[] amountArr = new int[amount + 1];
        // 最大金额的最大硬币数量 amount + 1
        int max = amount + 1;
        Arrays.fill(amountArr, max);
        amountArr[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    amountArr[i] = Math.min(amountArr[i], amountArr[i - coin] + 1);
                }
            }
        }
        return amountArr[amount] == amount + 1 ? -1 : amountArr[amount];
    }

    public static int coinChange(int[] coins, int amount) {
        return solution(coins, amount);
    }
}
