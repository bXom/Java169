package com.bxom.coreservice.leetcode75.W3;

import lombok.extern.slf4j.Slf4j;

import java.util.Deque;
import java.util.LinkedList;

@Slf4j
public class W0308 {
    // 试题
    // leetcode-150
    // https://leetcode.cn/problems/evaluate-reverse-polish-notation/
    public static void main(String[] args) {
        String[] tokens = new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        log.info("result: {}", evalRPN(tokens));
    }

    public static int evalRPN(String[] tokens) {
        return solution1(tokens);
    }

    private static int solution1(String[] tokens) {
        int size = tokens.length;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            String s = tokens[i];
            switch (s) {
                case "+":
                    calAdd(stack);
                    break;
                case "-":
                    calSub(stack);
                    break;
                case "*":
                    calMul(stack);
                    break;
                case "/":
                    calDiv(stack);
                    break;
                default:
                    stack.push(Integer.parseInt(s));
                    break;
            }
        }
        return stack.pop();
    }

    private static void calAdd(Deque<Integer> stack) {
        int b = stack.pop();
        int a = stack.pop();
        stack.push(a + b);
    }

    private static void calSub(Deque<Integer> stack) {
        int b = stack.pop();
        int a = stack.pop();
        stack.push(a - b);
    }

    private static void calMul(Deque<Integer> stack) {
        int b = stack.pop();
        int a = stack.pop();
        stack.push(a * b);
    }

    private static void calDiv(Deque<Integer> stack) {
        int b = stack.pop();
        int a = stack.pop();
        stack.push(a / b);
    }
}
