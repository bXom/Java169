package com.bxom.coreservice.leetcode75;

import java.util.ArrayList;
import java.util.List;

public class W0402 {
    /**
     * leetcode-208
     * https://leetcode.cn/problems/implement-trie-prefix-tree/description/
     *
     * @param args
     */
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
    }

    static class Trie {
        private List<String> wordList;
        private List<String> prefixList;

        Trie() {
            this.wordList = new ArrayList<>();
            this.prefixList = new ArrayList<>();
        }

        public void insert(String word) {

        }

        public boolean search(String word) {
            return false;
        }

        public boolean startsWith(String prefix) {
            return false;
        }
    }
}
