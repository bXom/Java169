package com.bxom.coreservice.leetcode75.W4;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

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
        Trie[] children = trie.children;
        boolean isEnd = trie.isEnd;
        while (!isEnd) {
            for (int i = 0; i < 26; i++) {
                if (children[i] != null) log.info("{}", Character.toString(97 + i));
            }
            for (Trie child : children) {
                if (child != null) {
                    children = child.children;
                    isEnd = child.isEnd;
                }
            }
        }

//        Trie trie = new Trie();
//        trie.insert("apple");
//        log.info("words: ------------");
//        trie.getWordsSet().forEach(log::info);
//        log.info("prefix: ------------");
//        trie.getPrefixSet().forEach(log::info);
    }

    @Data
    static class Trie {
        private Trie[] children;
        private boolean isEnd;

        Trie() {
            this.children = new Trie[26];
            this.isEnd = false;
        }

        public void insert(String word) {
            int length = word.length();
            Trie node = this;
            for (int i = 0; i < length; i++) {
                int index = word.charAt(i) - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            Trie node = searchPrefix(word);
            return node != null && node.isEnd;
        }

        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
        }

        private Trie searchPrefix(String word) {
            Trie node = this;
            int length = word.length();
            for (int i = 0; i < length; i++) {
                int index = word.charAt(i) - 'a';
                if (node.children[index] == null) {
                    return null;
                }
                node = node.children[index];
            }
            return node;
        }
    }
//    @Data
//    static class Trie {
//        private Set<String> wordsSet;
//        private Set<String> prefixSet;
//
//        Trie() {
//            this.wordsSet = new HashSet<>();
//            this.prefixSet = new HashSet<>();
//        }
//
//        public void insert(String word) {
//            this.wordsSet.add(word);
//            int length = word.length();
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < length; i++) {
//                sb.append(word.charAt(i));
//                this.prefixSet.add(sb.toString());
//            }
//        }
//
//        public boolean search(String word) {
//            return this.wordsSet.contains(word);
//        }
//
//        public boolean startsWith(String prefix) {
//            return this.prefixSet.contains(prefix);
//        }
//    }
}
