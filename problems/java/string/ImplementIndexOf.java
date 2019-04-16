package string;

/**
 * @author BlackSugar
 * @date 2019/4/16
 * Implement strStr().
 * <p>
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * <p>
 * Example 1:
 * <p>
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Example 2:
 * <p>
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * Clarification:
 * <p>
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 * <p>
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 */
public class ImplementIndexOf {
    /**
     * 实现String indexOf方法
     * 思路：感觉这题有点。。。肯定不能直接使用内置的indexOf()，
     * 1、先求出needle的长度，再使用substring判断haystack存不存在这个自字符串并获取索引
     * 2、如果不使用所有内置方法的话，那么只能迭代了，循环判断haystack不同的头开始往后每个字符都相等
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (null == needle || needle.length() == 0) {
            return 0;
        }
        if (null != haystack && haystack.length() > 0) {
            int length = needle.length();
            /*for (int i = 0; i <= haystack.length() - length; i++) {
                if (haystack.substring(i, i + length).equals(needle)) {
                    return i;
                }
            }*/
            for (int i = 0; i <= haystack.length() - length; i++) {
                for (int j = 0; j < length; j++) {
                    if (haystack.charAt(i + j) != needle.charAt(j)) {
                        break;
                    }
                    if (j + 1 == length) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new ImplementIndexOf().strStr("hello", "ll"));
    }
}
