package string;

import java.util.Stack;

/**
 * @author BlackSugar
 * @date 2019/4/16
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * 1.Open brackets must be closed by the same type of brackets.
 * 2.Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 * <p>
 * Example 1:
 * <p>
 * Input: "()"
 * Output: true
 * Example 2:
 * <p>
 * Input: "()[]{}"
 * Output: true
 * Example 3:
 * <p>
 * Input: "(]"
 * Output: false
 * Example 4:
 * <p>
 * Input: "([)]"
 * Output: false
 * Example 5:
 * <p>
 * Input: "{[]}"
 * Output: true
 */
public class ValidParentheses {
    /**
     * 验证括号字符串是否合法
     * 思路：利用栈，先判断长度奇偶，再遍历把相应右括号push进栈中，直到出现右括号并比较是否和top相等，不相等则不合法
     * ((){[})--->))+)--->)}]+}--->false
     * ((){[]})--->))+)--->)}]+]--->)}+}--->)+)--->true
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (null == s || s.length() == 0) {
            return true;
        }
        if ((s.length() & 1) == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '{':
                    stack.push('}');
                    break;
                case '[':
                    stack.push(']');
                    break;
                case '(':
                    stack.push(')');
                    break;
                default:
                    if (stack.empty() || stack.pop() != c) {
                        return false;
                    }
            }
        }
        return stack.empty();
    }

    public static void main(String[] args) {
        System.out.println(new ValidParentheses().isValid("{}()"));
    }
}
