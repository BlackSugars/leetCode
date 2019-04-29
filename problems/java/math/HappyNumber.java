package math;

import java.util.*;

/**
 * @author BlackSugar
 * @date 2019/4/29
 * Write an algorithm to determine if a number is "happy".
 * <p>
 * A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
 * <p>
 * Example:
 * <p>
 * Input: 19
 * Output: true
 * Explanation:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 */
public class HappyNumber {
    /**
     * 判断是否快乐数字
     * 思路：
     * 1、将数字存入list中，如果重复，则有回路，所以不是快乐数字
     * 2、递归
     * 3、找规律结束循环（后来发现是1、7）
     * 大神思路：
     * 1、预先计算出快乐数字，然后直接查找（看不太懂，贴下来）
     * Traditional solution to this problem is :
     * calculate the sum of the square of each digit until it is equal to 1 or has a recurrence
     * However,there is no need to calculate the sum every time.
     * As we all know,
     * ①、each digit's range is [0,9]
     * ②、the range of Integer is [0,2^31-1],that is [0,10] in number of digits
     * which means the square sum of each digit would always be in range[0 , 9 * 9 * 10) or [0 , 810)
     * to speed up our processing,we can pre-calculate the happy numbers in range[0. 810)
     * and store them in our program for later lookups
     * Here is my implementation:
     * //this is the bitset representation of happy numbers in range[0,810)
     * private static final long[]happyMark = new long[]{580548859274370L, 35812649762896L,
     *                           5764889547766761510L, 158621866461187L, -9061136725337702208L,
     *                          -8574391826910016959L, 4683743612499927428L, 286423854940160L,
     *                           29290989780729856L, 7566260683533189120L, 1170945809492058640L,
     *                           720593571220033568L, 292095590400L};
     *
     * public boolean isHappy(int n) {
     *     if( n>810 ){
     *         int sum = 0, remainder = 0;
     *         while( n>0 ){
     *             remainder = n%10;
     *             sum += remainder*remainder;
     *             n /= 10;
     *         }
     *         n = sum;
     *     }
     *     int idx = ( n>>6 );
     *     long bitRep = ( 1L<< (n&0x3f) );
     *     return ( happyMark[idx]&bitRep )!=0;
     * }
     * <p>
     * 2、发现快乐数字是1、7，然后直接计算（或者1是，4不是）
     * public boolean isHappy(int n) {
     *         if(n==1 || n==7) return true;
     *         else if(n<10) return false;
     *         int d=0;
     *         while(n>0){
     *             d+=(n%10)*(n%10);
     *             n/=10;
     *         }
     *         return isHappy(d);
     *     }
     *<p>
     *     public boolean isHappy(int n) {
     *      while (true) {
     *             int next_n = helper(n);
     *             if(next_n == n) {
     *                 break;
     *             }
     *             if(next_n == 4) {
     *                 return false;
     *             }
     *             n = next_n;
     *         }
     *         if(n == 1) {
     *             return true;
     *         } else {
     *             return false;
     *         }
     *     }
     *     private int helper(int val) {
     *         int result = 0;
     *         while (val != 0) {
     *             int remain = val % 10;
     *             result += remain * remain;
     *             val /= 10;
     *         }
     *         return result;
     *     }
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        List<Integer> list = new ArrayList<>();

        /*int i = n;
        while (!list.contains(i)) {
            list.add(i);
            n = list.get(list.size() - 1);
            i = 0;
            while (n != 0) {
                i += n % 10 * (n % 10);
                n = n / 10;
            }
            if (i == 1) {
                return true;
            }
        }
        return false;*/

        return isHappyHelper(n, list);
    }

    private boolean isHappyHelper(int n, List<Integer> list) {
        //base case
        if (n == 1) {
            return true;
        }
        if (list.contains(n)) {
            return false;
        }
        list.add(n);
        int i = 0;
        while (n != 0) {
            i += n % 10 * (n % 10);
            n = n / 10;
        }

        return isHappyHelper(i, list);
    }

    public static void main(String[] args) {
        System.out.println(new HappyNumber().isHappy(19));
    }
}
