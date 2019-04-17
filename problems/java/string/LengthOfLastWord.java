package string;

import java.util.Arrays;

/**
 * @author BlackSugar
 * @date 2019/4/17
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
 * <p>
 * If the last word does not exist, return 0.
 * <p>
 * Note: A word is defined as a character sequence consists of non-space characters only.
 * <p>
 * Example:
 * <p>
 * Input: "Hello World"
 * Output: 5
 */
public class LengthOfLastWord {
    /**
     * 获取字符串最后一个单词的长度
     * 思想：lastIndexOf、split
     *
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        s = s.trim();
        int index = s.lastIndexOf(" "), length = s.length();
        return length - 1 - index;
    }

    public static void main(String[] args) {
        System.out.println(new LengthOfLastWord().lengthOfLastWord("a "));
    }
}
