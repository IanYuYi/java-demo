package com.example.demo.test;

import java.util.Arrays;
import java.util.List;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * 示例 1：
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * <p>
 * 示例 2:
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 * <p>
 * <p>
 * 提示：
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 **/
public class PreHeadDemo {

    public static String process(List<String> arr) {
        if (arr == null || arr.size() == 0) {
            return "";
        }

        String prefix = arr.get(0);
        for (int i = 1; i < arr.size(); i++) {
            while (arr.get(i).indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "null";
                }
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        List<String> arr_1 = Arrays.asList("flower", "flow", "flight");
        List<String> arr_2 = Arrays.asList("dog","racecar","car");

        String result_1 = process(arr_1);
        String result_2 = process(arr_2);

        System.out.println(result_1);
        System.out.println(result_2);
    }
}
