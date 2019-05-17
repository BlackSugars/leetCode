package string;

import java.util.Arrays;

/**
 * @author BlackSugar
 * @date 2019/5/17
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "rat", t = "car"
 * Output: false
 * Note:
 * You may assume the string contains only lowercase alphabets.
 * <p>
 * Follow up:
 * What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */
public class ValidAnagram {
    /**
     * 判断是不是同字母异序词
     * 思路：
     * 1、简单的思路肯定是遍历两个字符串，将对应的[字母-个数]放进map中或者ascii码桶中，如果相同则为true
     * 2、将两个字符串排序，看是不是一致
     * 3、因为偶数个异或为0，将两个字符串的所有字符的ascii码异或起来，为0则为true，
     * 事实证明这种方法不可行，比如："xaaddy"+"xbbccy"、"aaab"+"bbba"
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] array = new int[26];
        for (int i = 0; i < s.length(); i++) {
            array[s.charAt(i) - 'a']++;
            array[t.charAt(i) - 'a']--;
        }
        for (int n : array) {
            if (n != 0) {
                return false;
            }
        }
        return true;
        /*char[] a = s.toCharArray();
        char[] b = t.toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);
        return Arrays.equals(a, b);*/
    }

    public static void main(String[] args) {
        System.out.println(new ValidAnagram().isAnagram("xaaddy", "xbbccy"));
    }
}
