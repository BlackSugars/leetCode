package math;

/**
 * @author BlackSugar
 * @date 2019/4/16
 * Given a 32-bit signed integer, reverse digits of an integer.
 * <p>
 * Example 1:
 * <p>
 * Input: 123
 * Output: 321
 * Example 2:
 * <p>
 * Input: -123
 * Output: -321
 * Example 3:
 * <p>
 * Input: 120
 * Output: 21
 * Note:
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1].
 * For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */
public class Reverse {
    /**
     * 翻转int
     * 思路：转为StringBuilder 利用其方法；符号单独处理
     * 一般思路:利用%10类似于栈进行翻转（判断是否回文方法当中使用的方法）
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        StringBuilder s = new StringBuilder(Integer.valueOf(x).toString());
        String reverse = x >= 0 ? s.reverse().toString() : s.deleteCharAt(0).reverse().insert(0, "-").toString();
        long re = Long.valueOf(reverse);
        if (re > Integer.MAX_VALUE || re < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) re;
    }

    public static void main(String[] args) {
        System.out.println(new Reverse().reverse(123));
    }
}
