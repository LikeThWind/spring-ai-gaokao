package com.example.modelday1.demo;

import java.util.Arrays;

/**
 * 拼接最大数问题
 * 重新排列非负整数，组成最大的整数
 */
public class LargestNumber {

    /**
     * 方法：自定义排序
     * 核心思想：对于两个数 a 和 b，比较 ab 和 ba 的字典序
     * 如果 ab > ba，则 a 应该排在 b 前面
     * 
     * @param nums 非负整数数组
     * @return 组成的最大整数字符串
     */
    public static String largestNumber(int[] nums) {
        // 边界检查
        if (nums == null || nums.length == 0) {
            return "";
        }

        // 将整数转换为字符串数组
        String[] strNums = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strNums[i] = String.valueOf(nums[i]);
        }

        // 自定义排序规则
        // 对于两个字符串 a 和 b，比较 ab 和 ba 的字典序
        Arrays.sort(strNums, (a, b) -> {
            String order1 = a + b;  // a 在前
            String order2 = b + a;  // b 在前
            // 降序排序：如果 order2 > order1，返回正数，b 排前面
            return order2.compareTo(order1);
        });

        // 特殊情况：如果最大的数是 "0"，说明所有数都是 0
        if (strNums[0].equals("0")) {
            return "0";
        }

        // 拼接结果
        StringBuilder result = new StringBuilder();
        for (String num : strNums) {
            result.append(num);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // 示例 1
        int[] nums1 = {10, 2};
        System.out.println("输入：[10, 2]");
        System.out.println("输出：" + largestNumber(nums1)); // "210"
        System.out.println();

        // 示例 2
        int[] nums2 = {3, 30, 34, 5, 9};
        System.out.println("输入：[3, 30, 34, 5, 9]");
        System.out.println("输出：" + largestNumber(nums2)); // "9534330"
        System.out.println();

        // 测试用例 3：全是 0
        int[] nums3 = {0, 0, 0};
        System.out.println("输入：[0, 0, 0]");
        System.out.println("输出：" + largestNumber(nums3)); // "0"
        System.out.println();

        // 测试用例 4：单个元素
        int[] nums4 = {1};
        System.out.println("输入：[1]");
        System.out.println("输出：" + largestNumber(nums4)); // "1"
        System.out.println();

        // 测试用例 5：相同数字
        int[] nums5 = {5, 5, 5, 5};
        System.out.println("输入：[5, 5, 5, 5]");
        System.out.println("输出：" + largestNumber(nums5)); // "5555"
        System.out.println();

        // 测试用例 6：复杂情况
        int[] nums6 = {824, 938, 1399, 5607, 6973, 5703, 9609, 4398, 8247};
        System.out.println("输入：[824, 938, 1399, 5607, 6973, 5703, 9609, 4398, 8247]");
        System.out.println("输出：" + largestNumber(nums6));
    }
}
