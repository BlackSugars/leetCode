package math;

/**
 * @author BlackSugar
 * @date 2019/4/17
 * Given two binary strings, return their sum (also a binary string).
 * <p>
 * The input strings are both non-empty and contains only characters 1 or 0.
 * <p>
 * Example 1:
 * <p>
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 * <p>
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 */
public class AddBinary {
    /**
     * 二进制加法
     * 思路：
     * 1、就是字符串加法，遇2进位，根据字符串在短的前面补0，在逆序迭代
     * 2、不补0，采用双指针
     * 3、char不使用Integer.valueOf(String.valueOf(a.charAt(i)))来转为int，而采用char - '0'，让他使用ascii码计算
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        /*int length = Math.abs(a.length() - b.length());
        StringBuilder zero = new StringBuilder();
        for (int i = 0; i < length; i++) {
            zero.append("0");
        }
        if (a.length() > b.length()) {
            b = new StringBuilder(b).insert(0, zero).toString();
        } else {
            a = new StringBuilder(a).insert(0, zero).toString();
        }*/

        boolean plus = false;
        StringBuilder sb = new StringBuilder();
        for (int i = a.length() - 1, j = b.length() - 1, num = 0; i >= 0 || j >= 0; ) {
            if (i >= 0) {
                num += a.charAt(i--) - '0';
            }
            if (j >= 0) {
                num += b.charAt(j--) - '0';
            }
            num = plus ? num + 1 : num;
            if (num > 1) {
                plus = true;
                num -= 2;
            } else {
                plus = false;
            }
            sb.append(num);
            num = 0;
        }
        return plus ? sb.append(1).reverse().toString() : sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new AddBinary().addBinary("1010", "1011"));
    }
}
