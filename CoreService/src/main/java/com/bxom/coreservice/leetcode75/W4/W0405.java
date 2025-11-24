package com.bxom.coreservice.leetcode75.W4;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class W0405 {
    /**
     * leetcode-155
     * https://leetcode.cn/problems/min-stack/
     *
     * @param args
     */
    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-1);
        log.info("result: {}", stack.stack);
        log.info("miniIndex: {}", stack.miniIndex);
        log.info("getMin: {}", stack.getMin());
        log.info("top: {}", stack.top());
        stack.pop();
        log.info("getMin: {}", stack.getMin());
    }

    static class MinStack {
        private int endIndex;
        private int[] miniIndex;
        private int[] stack;

        MinStack() {
            this.endIndex = -1;
            this.miniIndex = new int[5];
            this.stack = new int[5];
            Arrays.fill(this.miniIndex, Integer.MAX_VALUE);
            Arrays.fill(this.stack, Integer.MAX_VALUE);
        }

        public void push(int val) {
            // 判断是否需要扩容
            if (this.endIndex == this.stack.length - 1) {
                resizeStack(true);
            }
            // 写入新值，移动结尾指针
            this.stack[++this.endIndex] = val;
            // 修改最小值指针
            if (this.endIndex == 0) {
                this.miniIndex[this.endIndex] = 0;
            } else if (this.stack[this.miniIndex[this.endIndex - 1]] > val) {
                this.miniIndex[this.endIndex] = this.endIndex;
            } else {
                this.miniIndex[this.endIndex] = this.miniIndex[this.endIndex - 1];
            }
        }

        private void resizeStack(boolean isAdd) {
            if (isAdd) {
                int length = this.stack.length;
                int[] newStack = new int[length + 5];
                int[] newMiniStack = new int[length + 5];
                Arrays.fill(newStack, Integer.MAX_VALUE);
                Arrays.fill(newMiniStack, Integer.MAX_VALUE);
                System.arraycopy(this.stack, 0, newStack, 0, length);
                System.arraycopy(this.miniIndex, 0, newMiniStack, 0, length);
                this.stack = newStack;
                this.miniIndex = newMiniStack;
            } else {
                int length = this.stack.length;
                int[] newStack = new int[length - 5];
                int[] newMiniStack = new int[length - 5];
                System.arraycopy(this.stack, 0, newStack, 0, length - 5);
                System.arraycopy(this.miniIndex, 0, newMiniStack, 0, length - 5);
                this.stack = newStack;
                this.miniIndex = newMiniStack;
            }
        }

        public void pop() {
            if (this.endIndex == -1) return;
            this.stack[this.endIndex] = Integer.MAX_VALUE;
            this.miniIndex[this.endIndex] = Integer.MAX_VALUE;
            this.endIndex--;
        }

        public int top() {
            return this.stack[this.endIndex];
        }

        public int getMin() {
            return this.stack[this.miniIndex[this.endIndex]];
        }
    }
}
