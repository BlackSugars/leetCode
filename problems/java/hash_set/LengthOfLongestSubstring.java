package hash_set;

import java.util.HashSet;
import java.util.Set;

/**
 * @author BlackSugar
 * @date 2019/4/15
 * iven a string, find the length of the longest substring without repeating characters.
 * <p>
 * Example 1:
 * <p>
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 * <p>
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * <p>
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LengthOfLongestSubstring {
    /**
     * 思路：
     * 1、最开始使用了双指针双循环迭代，但是时间开销太大，达到O(n^2)
     * 2、使用了hashset不能添加重复元素的特性，当添加失败的时候，移除set头对象，直到添加成功继续迭代
     * Input: "pwwkew"--->[p]--->[pw]--->[w]--->[]--->[w]--->[wk]---[wke]...
     * <p>
     * 其他大神思路：
     * 3、可以使用hashmap保存最新的重复元素索引，相对于方法2，减少了重复删除的步骤
     * 4、如果我们知道字符集很小，可以使用ASCII码字符串代替hashmap
     * int[26] for Letters 'a' - 'z' or 'A' - 'Z'
     * int[128] for ASCII
     * int[256] for Extended ASCII
     *
     * @param s 输入字符串
     * @return 无重复自字符串长度
     */
    public int lengthOfLongestSubstring(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        if (length == 1) {
            return length;
        }
        int maxLength = 0, i = 0, j = 1;
        Set<Character> set = new HashSet<>(length);
        set.add(s.charAt(i));
        while (j < length) {
            if (set.add(s.charAt(j))) {
                j++;
                int subLength = set.size();
                maxLength = maxLength > subLength ? maxLength : subLength;
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring("adasdasdadwqwqwer"));
    }
}
