package math;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BlackSugar
 * @date 2019/4/30
 * Count the number of prime numbers less than a non-negative number, n.
 * <p>
 * Example:
 * <p>
 * Input: 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 */
public class CountPrimes {
    /**
     * 数素数
     * 思路：最直接的应该是遍历，每个数字判断是不是素数，是就++，直到n
     * 1、判断过程是从1开始求余，直到数字本身都没有0的话，则为素数，此处不写 O(n^2)
     * 2、因为非素数=a*b，那么a<=sqrt(n)<=b，12=3*4--->3<=sqrt(12)<=4 所以我们知道求素数可以只求前根号i个
     * 3、大神算法：Sieve of Eratosthenes
     * ①、先把2和所有奇数设为true
     * ②、循环把所有素数的倍数改为false，直到根号n
     * ③、把素数数出来
     * public int countPrimes(int n) {
     *         //Sieve
     *         if (n <= 2) return 0;
     *         boolean sieve[] = new boolean[n+1];
     *         sieve[2] = true;
     *         for(int i = 3; i < n; i+=2) {
     *             sieve[i] = true;
     *         }
     *
     *         for (int p = 3; p*p<=n; p++){
     *             if (sieve[p]){
     *                 for (int i = p+p; i<=n; i += p ){
     *                     sieve[i] = false;
     *                 }
     *             }
     *         }
     *         int count = 1;
     *         for (int i = 3; i < n; i+=2){
     *             count += sieve[i] ? 1:0;
     *         }
     *         return count;
     *     }
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }

    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new CountPrimes().countPrimes(100));
    }
}
