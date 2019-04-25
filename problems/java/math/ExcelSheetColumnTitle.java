package math;


/**
 * @author BlackSugar
 * @date 2019/4/25
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * <p>
 * For example:
 * <p>
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 * ...
 * Example 1:
 * <p>
 * Input: 1
 * Output: "A"
 * Example 2:
 * <p>
 * Input: 28
 * Output: "AB"
 * Example 3:
 * <p>
 * Input: 701
 * Output: "ZY"
 */
public class ExcelSheetColumnTitle {
    /**
     * 将数字转为对应字符
     * 思路：以26为单位进位，相当于26进制
     * 701=26^2+25
     * 大神思路：
     * 通过ascii码相加，(char)('A' + n % 26)
     *
     * @param n
     * @return
     */

    public String convertToTitle(int n) {
        char[] map = {'Z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y'};
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.insert(0, map[(n % 26)]);
            n = n % 26 == 0 ? n / 26 - 1 : n / 26;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new ExcelSheetColumnTitle().convertToTitle(52));
    }
}
