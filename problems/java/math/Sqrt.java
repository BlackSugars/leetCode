package math;

/**
 * @author BlackSugar
 * @date 2019/4/18
 * Implement int sqrt(int x).
 * <p>
 * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 * <p>
 * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
 * <p>
 * Example 1:
 * <p>
 * Input: 4
 * Output: 2
 * Example 2:
 * <p>
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since
 * the decimal part is truncated, 2 is returned.
 */
public class Sqrt {
    /**
     * 平方根整数部分
     * 思路：肯定不能用Math.sqrt() :)
     * 1、奇葩思路，因为平方增长得比较快，所以遍历整数，直到平方大于所需值，则平方根整数部分为n-1，
     * 因为整形最大为2^31-1，所以需要排除比2147395600(46340^2)大的数字，空间为O(1)，时间太长
     * 2、我们知道边界在46340的话，采用二分法，左右夹逼
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        /*return (int) Math.sqrt(x);*/

        int i = 1;
/*        if (x >= 2147395600) {
            return 46340;
        }
        while (i * i <= x) {
            i++;
        }
        return i - 1;*/

        int j = 46340;
        int mid;
        while (i <= j) {
            mid = (i + j) / 2;
            if (mid * mid < x) {
                i = mid + 1;
            } else if (mid * mid > x) {
                j = mid - 1;
            } else {
                return mid;
            }
        }
        return j;
    }

    public static void main(String[] args) {
        System.out.println(new Sqrt().mySqrt(8));
    }
}
