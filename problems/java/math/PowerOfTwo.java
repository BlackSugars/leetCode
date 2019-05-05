package math;

/**
 * @author BlackSugar
 * @date 2019/5/5
 * Given an integer, write a function to determine if it is a power of two.
 * <p>
 * Example 1:
 * <p>
 * Input: 1
 * Output: true
 * Explanation: 2^0 = 1
 * Example 2:
 * <p>
 * Input: 16
 * Output: true
 * Explanation: 2^4 = 16
 * Example 3:
 * <p>
 * Input: 218
 * Output: false
 */
public class PowerOfTwo {
    /**
     * 是否是2^n
     * 思路：
     * 1、最直接的思路应该是：因为小于2^31-1，所以在小于n的情况下直接循环到30，看存不存在，或者直接将所有2的次方数放入内存中再比较之类的
     * 2、还有种直接的思路是：n除以2，看结果是不是奇数，我们这里用递归来做吧
     * 3、因为2^n的二进制格式为100000...，所以可以判断他只有一个1即可，Integer.bitCount()，这里就不写了
     * 大神思路：这里10000 & 1111==0，其实就是思路三
     * return (n > 0 && (n & (n - 1)) == 0);
     * return n>0&&1073741824%n==0;
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        /*for (int i = 0; i < 32 && 1 << i <= n; i++) {
            if (1 << i == n) {
                return true;
            }
        }
        return false;*/

        if (n == 1) {
            return true;
        }
        if ((n & 1) == 1 || n <= 0) {
            return false;
        }
        return isPowerOfTwo(n >> 1);
    }

    public static void main(String[] args) {
        System.out.println(new PowerOfTwo().isPowerOfTwo(8));
    }
}
