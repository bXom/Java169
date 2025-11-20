package com.bxom.coreservice.leetcode75;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Slf4j
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
        log.info("words: ------------");
        trie.getWordsSet().forEach(log::info);
        log.info("prefix: ------------");
        trie.getPrefixSet().forEach(log::info);
    }

    @Data
    static class Trie {
        private Set<String> wordsSet;
        private Set<String> prefixSet;

        Trie() {
            this.wordsSet = new HashSet<>();
            this.prefixSet = new HashSet<>();
        }

        public void insert(String word) {
            this.wordsSet.add(word);
            int length = word.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                sb.append(word.charAt(i));
                this.prefixSet.add(sb.toString());
            }
        }

        public boolean search(String word) {
            return this.wordsSet.contains(word);
        }

        public boolean startsWith(String prefix) {
            return this.prefixSet.contains(prefix);
        }
    }
}
