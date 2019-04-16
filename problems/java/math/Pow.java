package math;

/**
 * @author BlackSugar
 * @date 2019/4/16
 * Implement pow(x, n), which calculates x raised to the power n (xn).
 * <p>
 * Example 1:
 * <p>
 * Input: 2.00000, 10
 * Output: 1024.00000
 * Example 2:
 * <p>
 * Input: 2.10000, 3
 * Output: 9.26100
 * Example 3:
 * <p>
 * Input: 2.00000, -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 * Note:
 * <p>
 * -100.0 < x < 100.0
 * n is a 32-bit signed integer, within the range [−2^31, 2^31 − 1]
 */
public class Pow {
    /**
     * 求次方
     * 思路：
     * 符号问题：最开始想取绝对值计算，然后根据正负返回re还是1/re。
     * 但是因为负数最小值为2^23，直接取绝对值会溢出，所以对负数进行处理，保存为-n - 1，最后判断为负则单独再除以初始值即可
     * 1、直接迭代（此处未列出），一闪而过的思路，直接循环n次*法，用temp保存x初始值，x按照x*temp变化，时间会溢出
     * x=2，n=5--->2*1(i=1)--->2*2(i=2)--->4*2(i=3)--->8*2(i=4)--->16*2(i=5)
     * 2、还是迭代（此处也未列出），和1不同的是，i按照<<1来进位，x按照x*x指数变化，直到最近的2的次方，剩下的需要再次从j=1开始
     * x=2，n=5--->2*1(i=1)--->2*2(i=2)--->4*4(i=4)--->16*2(j=2)
     * 3、迭代，和2不同的是反着来，因为2^5次方可以视为2*2^4--->2*4^2--->2*16^1--->2*16,相当于遇到奇数次幂则单独乘以当前值，
     * 偶数次幂则可以转为(x^2)^(n/2)，所有迭代，遇到奇数次幂则结构乘以当前值，剩下的继续迭代，n以>>1速度递减直到为0
     * x=2,n=5,re=1--->x=2,n=4,re=2---->x=4,n=2,re=2---->x=16,n=1,re=2--->x=16,n=0,re=2*16
     * 4、递归，和3思路一样，按照x*x,n>>1递归，遇到奇数则与re相乘，负数需要单独处理为递归1/x*1/x,n=-n>>1
     *
     * @param x 传入值
     * @param n n次方，范围[−2^31, 2^31 − 1]
     * @return x^n
     */
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        int m = n > 0 ? n : -n - 1;
        double temp = x;
        double re = 1;
        while (m > 0) {
            if ((m & 1) == 1) {
                re *= x;

            }
            x *= x;
            m >>= 1;
        }

//            double re = myPow(x * x, m >> 1);
//            re = (m & 1) = 1 ? re * x : re;
        return n > 0 ? re : 1 / re / temp;
    }

    public static void main(String[] args) {
        System.out.println(new Pow().myPow(3, 5));
    }
}
