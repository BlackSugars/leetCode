package string;

/**
 * @author BlackSugar
 * @date 2019/4/16
 * Write a function to find the longest common prefix string amongst an array of strings.
 * <p>
 * If there is no common prefix, return an empty string "".
 * <p>
 * Example 1:
 * <p>
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 * <p>
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 */
public class LongestCommonPrefix {
    /**
     * 获取最长的前缀
     * 思路：截取前几位，遍历其余字符串，直到前缀不相等
     * （可以先排序找到最短字符串与其他遍历；或者找出最长与最短字符串相互比较）
     * 其他思路：indexof、charAt等方法
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        String pre = "";
        if (null == strs || strs.length == 0) {
            return pre;
        }
        String s = strs[0];
        if (strs.length == 1) {
            return s;
        }
        for (int i = 0; i <= s.length(); i++) {
            pre = s.substring(0, i);
            for (int j = 1; j < strs.length && i > 0; j++) {
                if (!strs[j].startsWith(pre)) {
                    return pre.substring(0, pre.length() - 1);
                }
            }
        }
        return pre;
    }

    public static void main(String[] args) {
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{"asdf", "asd", "asq", "ascf"}));
    }
}
