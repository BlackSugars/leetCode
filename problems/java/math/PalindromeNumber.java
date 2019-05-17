package math;

/**
 * @author BlackSugar
 * @date 2019/4/16
 * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
 * <p>
 * Example 1:
 * <p>
 * Input: 121
 * Output: true
 * Example 2:
 * <p>
 * Input: -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 * Example 3:
 * <p>
 * Input: 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 * <p>
 * Follow up:
 * <p>
 * Coud you solve it without converting the integer to a string?
 */
public class PalindromeNumber {
    /**
     * 判断是不是回文
     * 思路：由于要求了不转为string，所以先倒序再比较，类似栈操作，%10的余数每次*10+新余数   123%10=3；123/10=12；
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }
        int temp = x, a = 0;
        while (x != 0) {
            a = a * 10 + x % 10;
            x = x / 10;
        }
        return a == temp;
    }

    public static void main(String[] args) {
        System.out.println(new PalindromeNumber().isPalindrome(12321));
    }
}
