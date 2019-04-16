package string;

/**
 * @author BlackSugar
 * @date 2019/4/16
 * The count-and-say sequence is the sequence of integers with the first five terms as following:
 * <p>
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * <p>
 * Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.
 * <p>
 * Note: Each term of the sequence of integers will be represented as a string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: 1
 * Output: "1"
 * Example 2:
 * <p>
 * Input: 4
 * Output: "1211"
 */
public class CountAndSay {
    /**
     * 获取 count-and-say sequence
     * 思路：
     * 1、直接迭代，使用StringBuilder，前半部分为需要读的数字，将读出的数字加入后半部分，直到前半部分数字读完，并删除前半部分数字。
     * 在遇到后一位数字与当前不同的时候存入读出的个数以及当前值的字符，并将count归位
     * n=3--->1--->111(前半部分为1，后半部分为11)--->11(删除前半部分数字)--->1121--->21
     * 2、递归，base case为n=1，依次获取到上一层的字符串，再依次读取，遇到不一样的则保存计数以及当前值，直到字符串读完为止
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {
            /*//Iteration
            StringBuilder sb = new StringBuilder("1");
            for (int i = 1; i < n; i++) {
                int length = sb.length();
                for (int j = 0, count = 1; j < length; j++, count++) {
                    if (j == length - 1) {
                        sb.append(count).append(sb.charAt(j));
                    } else if (sb.charAt(j + 1) != sb.charAt(j)) {
                        sb.append(count).append(sb.charAt(j));
                        count = 0;
                    }
                }
                sb.delete(0, length);
            }
            return sb.toString();*/

        //Recursion
        if (n == 1) {
            return "1";
        }
        String s = countAndSay(--n);
        char temp = s.charAt(0);
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == temp) {
                count++;
            } else {
                sb.append(count).append(temp);
                temp = s.charAt(i);
                count = 1;
            }
        }
        sb.append(count).append(temp);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new CountAndSay().countAndSay(5));
    }
}
