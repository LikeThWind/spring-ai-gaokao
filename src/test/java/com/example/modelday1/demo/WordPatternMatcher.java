package com.example.modelday1.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * 单词规律匹配问题
 * 判断字符串是否符合给定的模式
 */
public class WordPatternMatcher {

    /**
     * 判断字符串是否符合模式
     * @param pattern 模式字符串，如 "abba"
     * @param str 待匹配的字符串，如 "北京 杭州 杭州 北京"
     * @return 是否匹配
     */
    public static boolean match(String pattern, String str) {
        // 边界检查
        if (pattern == null || str == null) {
            return false;
        }

        // 将字符串按空格分割成单词数组
        String[] words = str.split(" ");

        // 如果模式长度和单词数量不匹配，直接返回 false
        if (pattern.length() != words.length) {
            return false;
        }

        // 建立字符到单词的映射关系
        Map<Character, String> charToWord = new HashMap<>();
        // 建立单词到字符的映射关系（用于双向验证）
        Map<String, Character> wordToChar = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            // 检查字符到单词的映射
            if (!charToWord.containsKey(c)) {
                // 如果这个字符还没有映射过，建立映射
                charToWord.put(c, word);
            } else {
                // 如果这个字符已经有映射，检查是否匹配当前单词
                if (!charToWord.get(c).equals(word)) {
                    return false;
                }
            }

//            // 检查单词到字符的映射（确保不同字符不能映射到同一个单词）
//            if (!wordToChar.containsKey(word)) {
//                wordToChar.put(word, c);
//            } else {
//                if (wordToChar.get(word) != c) {
//                    return false;
//                }
//            }
        }

        return true;
    }

    public static void main(String[] args) {
        // 测试用例 1：应该返回 true
        System.out.println("测试 1: " + match("abba", "北京 杭州 杭州 北京")); // true

        // 测试用例 2：应该返回 false
        System.out.println("测试 2: " + match("aabb", "北京 杭州 杭州 北京")); // false

        // 测试用例 3：应该返回 false（长度不匹配）
        System.out.println("测试 3: " + match("abc", "北京 杭州 杭州 南京")); // false

        // 测试用例 4：应该返回 false（acac 要求第 1、3 个词相同，但实际不同）
        System.out.println("测试 4: " + match("acac", "北京 杭州 北京 广州")); // false

        // 额外测试用例
        System.out.println("测试 5: " + match("abcd", "北京 上海 广州 深圳")); // true
        System.out.println("测试 6: " + match("aaaa", "北京 北京 北京 北京")); // true
        System.out.println("测试 7: " + match("abab", "猫 狗 猫 狗")); // true
    }
}
