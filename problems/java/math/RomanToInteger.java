package math;

/**
 * @author BlackSugar
 * @date 2019/4/16
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * <p>
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
 * <p>
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 * <p>
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.
 * <p>
 * Example 1:
 * <p>
 * Input: "III"
 * Output: 3
 * Example 2:
 * <p>
 * Input: "IV"
 * Output: 4
 * Example 3:
 * <p>
 * Input: "IX"
 * Output: 9
 * Example 4:
 * <p>
 * Input: "LVIII"
 * Output: 58
 * Explanation: L = 50, V= 5, III = 3.
 * Example 5:
 * <p>
 * Input: "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */
public class RomanToInteger {
    /**
     * 罗马数字转为阿拉伯数字
     * Symbol       Value
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 思路：由于罗马数字是从大到小排列，若前一位小于后一位则为特殊数字
     * 把所有字符代表的值加起来再剔除六个特殊的，减少的值为特殊字符前一个字符*2
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        int sum = getInteger(s.charAt(0)), pre = getInteger(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int next = getInteger(s.charAt(i));
            sum += next;
            //if pre_value is less than next_value: IV,IX,XL,XC,CD,CM
            if (pre < next) {
                sum -= pre * 2;
            }
            pre = next;
        }
        return sum;
    }

    private int getInteger(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }


    public static void main(String[] args) {
        System.out.println(new RomanToInteger().romanToInt("XII"));
    }
}
