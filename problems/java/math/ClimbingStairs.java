package math;

/**
 * @author BlackSugar
 * @date 2019/4/18
 * You are climbing a stair case. It takes n steps to reach to the top.
 * <p>
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * <p>
 * Note: Given n will be a positive integer.
 * <p>
 * Example 1:
 * <p>
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 * <p>
 * Input: 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 */
public class ClimbingStairs {
    /**
     * 爬楼梯
     * 思路：
     * 1、递归，根据每步做选择，有点类似树（时间溢出）O(n^2)
     * 2、将每一步的结果缓存，将时间减少一半，只走左树就能填满缓存数组了，遇到缓存数组有值的可以直接返回 O(n)
     * 3、DP算法，感觉和题目无关了，我们知道这题的数学规律为[n]=[n-1]+[n-2]，这里可以优化为只含三个对象的数组，每次改变对应i%3的值即可
     * 大神思路：Fibonacci公式等等。。。
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        /*int[] cache = new int[n + 1];
        return climbStairsHelper(n, cache);*/

        int[] a = new int[n + 1];
        a[0] = 1;
        a[1] = 1;
        for (int i = 2; i <= n; i++) {
            a[i] = a[i - 1] + a[i - 2];
        }
        return a[n];
    }

    private int climbStairsHelper(int n, int[] cache) {
        if (n <= 1) {
            return 1;
        }
        if (cache[n] != 0) {
            return cache[n];
        }
        int count1 = climbStairsHelper(n - 1, cache);
        int count2 = climbStairsHelper(n - 2, cache);
        cache[n] = count1 + count2;
        return cache[n];
    }

    public static void main(String[] args) {
        System.out.println(new ClimbingStairs().climbStairs(44));
    }
}
