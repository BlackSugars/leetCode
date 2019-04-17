package array;

import java.util.Arrays;

/**
 * @author BlackSugar
 * @date 2019/4/17
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 * <p>
 * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
 * <p>
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * Example 2:
 * <p>
 * Input: [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 */
public class PlusOne {
    /**
     * 数组值加一
     * 思路：看到这题突然想到一个奇葩解，转为int，加一后再放回数组 :)，(后续证明大数不行)
     * 正常思路应该是末位加一，注意进位，所以反向逆序或者递归
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        if (null == digits || digits.length == 0) {
            return new int[]{1};
        }
        //Iterator
        /*int i = digits.length - 1;
        boolean plus = false;
        while (i >= 0) {
            if (i == digits.length - 1 || plus) {
                digits[i] += 1;
            }
            if (digits[i] > 9) {
                digits[i--] = 0;
                plus = true;
            } else {
                return digits;
            }
        }
        int[] re = new int[digits.length + 1];
        re[0] = 1;
        return re;*/

        //Recursion
        return plusOneHelper(digits, digits.length - 1, false);

        //Conversion, digits must be int, or it will overflow
        /*StringBuilder sb = new StringBuilder(digits.length);
        for (int num : digits) {
            sb.append(num);
        }
        Integer val = Integer.valueOf(sb.toString()) + 1;
        String sval = String.valueOf(val);
        int[] re = new int[sval.length()];
        for (int i = 0; i < sval.length(); i++) {
            re[i] = Integer.parseInt(String.valueOf(sval.charAt(i)));
        }
        return re;*/
    }

    private int[] plusOneHelper(int[] digits, int i, boolean plus) {
        if (i == digits.length - 1 || plus) {
            digits[i] += 1;
        }
        //base case
        if (i == 0) {
            if (digits[i] > 9) {
                int[] re = new int[digits.length + 1];
                re[0] = 1;
                return re;
            }
            return digits;
        }
        if (digits[i] > 9) {
            digits[i] = 0;
            plus = true;
        } else {
            return digits;
        }
        return plusOneHelper(digits, i - 1, plus);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new PlusOne().plusOne(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0})));
    }
}
