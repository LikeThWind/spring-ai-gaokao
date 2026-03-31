package com.example.modelday1.demo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 无重复字符的最长子串
 * 滑动窗口经典问题
 */
public class LongestSubstringWithoutRepeating {

    /**
     * 方法 1：滑动窗口 + HashSet
     * 时间复杂度：O(n)
     * 空间复杂度：O(min(m,n))，m 是字符集大小
     */
    public static int lengthOfLongestSubstring(String s) {
        // 边界检查
        if (s == null || s.length() == 0) {
            return 0;
        }

        Set<Character> charSet = new HashSet<>();
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // 如果字符已存在，移动左指针直到移除该字符
            while (charSet.contains(currentChar)) {
                charSet.remove(s.charAt(left));
                left++;
            }

            // 将当前字符加入集合
            charSet.add(currentChar);

            // 更新最大长度
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    /**
     * 方法 2：滑动窗口 + HashMap（优化版）
     * 可以直接跳到重复字符的下一个位置，不需要逐个移动 left
     * 时间复杂度：O(n)
     * 空间复杂度：O(min(m,n))
     */
    public static int lengthOfLongestSubstringOptimized(String s) {
        // 边界检查
        if (s == null || s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> charIndexMap = new HashMap<>();
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // 如果字符已存在，更新左指针位置
            // Math.max 确保 left 不会回退
            if (charIndexMap.containsKey(currentChar)) {
                left = Math.max(left, charIndexMap.get(currentChar) + 1);
            }

            // 更新字符的最新位置
            charIndexMap.put(currentChar, right);

            // 更新最大长度
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        // 测试用例
        String[] testCases = {
            "abcabcbb",  // 3 -> "abc"
            "bbbbb",     // 1 -> "b"
            "pwwkew",    // 3 -> "wke"
            "",          // 0 -> ""
            "abcdef",    // 6 -> "abcdef"
            "aab",       // 2 -> "ab"
            "dvdf",      // 3 -> "vdf"
            "anviaj"     // 5 -> "nviaj"
        };

        System.out.println("===== 方法 1：HashSet =====");
        for (String s : testCases) {
            int result = lengthOfLongestSubstring(s);
            System.out.printf("输入：\"%s\" => 输出：%d%n", s, result);
        }

        System.out.println("\n===== 方法 2：HashMap(优化) =====");
        for (String s : testCases) {
            int result = lengthOfLongestSubstringOptimized(s);
            System.out.printf("输入：\"%s\" => 输出：%d%n", s, result);
        }

        // 详细演示一个例子
        System.out.println("\n===== 详细演示：\"abcabcbb\" =====");
        demoWithDetails("abcabcbb");
    }

    /**
     * 详细演示滑动窗口过程
     */
    private static void demoWithDetails(String s) {
        Set<Character> charSet = new HashSet<>();
        int left = 0;
        int maxLength = 0;
        String maxSubstring = "";

        System.out.println("字符串：" + s);
        System.out.println("长度：" + s.length());
        System.out.println();

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            System.out.printf("right=%d, 字符='%c': ", right, currentChar);

            while (charSet.contains(currentChar)) {
                char removed = s.charAt(left);
                charSet.remove(removed);
                System.out.printf("移除'%c', left=%d→%d ", removed, left - 1, left + 1);
                left++;
            }

            charSet.add(currentChar);
            int currentLength = right - left + 1;
            String currentSubstring = s.substring(left, right + 1);

            if (currentLength > maxLength) {
                maxLength = currentLength;
                maxSubstring = currentSubstring;
                System.out.printf("✓ 窗口：[%s], 长度：%d (更新最大值)%n", currentSubstring, currentLength);
            } else {
                System.out.printf("窗口：[%s], 长度：%d%n", currentSubstring, currentLength);
            }
        }

        System.out.println();
        System.out.println("最终结果：最长子串 \"" + maxSubstring + "\", 长度 = " + maxLength);
    }
}
