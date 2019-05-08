package math;

/**
 * @author BlackSugar
 * @date 2019/5/8
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
 * <p>
 * Example 1:
 * <p>
 * Input: [5,7]
 * Output: 4
 * Example 2:
 * <p>
 * Input: [0,1]
 * Output: 0
 */
public class BitwiseANDOfNumbersRange {
    /**
     * 范围内的位与
     * 思路：
     * 1、一般思路应该是遍历，把每个数位与即可，O(n)
     * 2、可以优化，我们可以发现如果m<=n%2则为0，O(n/2)
     * 3、还可以优化，因为变化都是从右边开始变化的，所以将两个数都向右移i位，直到两个数相等再向左移相同位数即可
     * [5,7]--->[101,111]>>1--->[10,11]>>1--->[1,1]<<2--->100，
     * 因为最大为2^31-1，所以最多右移29位
     * 其他思路：将右边置零直到相等
     * public int rangeBitwiseAnd(int m, int n) {
     *         while (m < n) { n &= n - 1; }
     *         return n;
     *     }
     *
     * @param m
     * @param n
     * @return
     */
    public int rangeBitwiseAnd(int m, int n) {
        /*if (m <= n >> 1) {
            return 0;
        }
        for (int i = m + 1; 0 < i && i <= n; i++) {
            m &= i;
            if (m == 0) {
                return 0;
            }
        }
        return m;*/
        for (int i = 0; i < 30; i++, m >>= 1, n >>= 1) {
            if (m == n) {
                return m << i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new BitwiseANDOfNumbersRange().rangeBitwiseAnd(5, 7));
    }
}
