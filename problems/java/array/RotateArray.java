package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author BlackSugar
 * @date 2019/4/26
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3,4,5,6,7] and k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * Example 2:
 * <p>
 * Input: [-1,-100,3,99] and k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 * Note:
 * * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * * Could you do it in-place with O(1) extra space?
 */
public class RotateArray {
    /**
     * 向右移动数组
     * 思路：题目都说了多弄点方法，先从简单点的来
     * 1、将[n-k,n-1]弄到新的数组int[k]中去，然后将剩下的数字索引+k，再把新数组的数字放进去，时间O(n)，空间O(k)，这个这里就不写了，是因为懒 :)
     * 2、其实题目提示了我们一步一步移，就是[n-1]--->[0]，把[0]保存下来，其他依次加，最后将保存值放入[1]，循环k次，
     * 这里又分为迭代和递归，只实现感觉难一点的递归，时间O(nk)，空间O(1)
     * 3、交换位置大法，将i移到i+k，i+k移到i+k+k...直到i+k+k+k%n==i，然后第二次循环，直到移动过的数字等于n，时间O(n)，空间O(1)
     * 4、之前看过这种移位的方法有倒序法，先整体颠倒，再颠倒[0,k-1]，颠倒就用交换大法好了，时间O(2n)--->O(n)，空间O(1)
     * 大神思路：
     * 1、颠倒的时候可以用异或：
     * nums[start] ^= nums[end];
     * nums[end] ^= nums[start];
     * nums[start] ^= nums[end];
     * 2、交换位置大法可以这样：
     * 和3类似，可以发现如果n%k=0，那么一直向前移动i个k就走完了一条路径，若n%k!=0，移动j个k就遍历完全了，
     * 那么首先将数据分成几条路，GCD = gcd(3,8) = 1, 这代表8个数字右移3只有一条路径
     * Count = (n / GCD) - 1 = 7, 意味着我们需要交换7次(事实上，我们需要交换路径上的元素个数的次数)
     * [1,2,3,4,5,6,7,8] (position = 3) -> [4,2,3,1,5,6,7,8] (position = 6) -> [7,2,3,1,5,6,4,8](position = 1)
     * -> [2,7,3,1,5,6,4,8](position = 4) -> [5,7,3,1,2,6,4,8](position = 7) -> [8,7,3,1,2,6,4,5](position = 2)
     * ->[3,7,8,1,2,6,4,5](position = 5) -> [6,7,8,1,2,3,4,5] -> finished, total 7 times swap. Final result [6,7,8,1,2,3,4,5]
     * 其实就相当于将上面3所保存的temp放入路径开始的位置进行保存，其他思想其实是一致的
     * <p>
     * public class Solution {
     * public void rotate(int[] nums, int k) {
     * if(nums.length <= 1){
     * return;
     * }
     * //step each time to move
     * int step = k % nums.length;
     * //find GCD between nums length and step
     * int gcd = findGcd(nums.length, step);
     * int position, count;
     * <p>
     * //gcd path to finish movie
     * for(int i = 0; i < gcd; i++){
     * //beginning position of each path
     * position = i;
     * //count is the number we need swap each path
     * count = nums.length / gcd - 1;
     * for(int j = 0; j < count; j++){
     * position = (position + step) % nums.length;
     * //swap index value in index i and position
     * nums[i] ^= nums[position];
     * nums[position] ^= nums[i];
     * nums[i] ^= nums[position];
     * }
     * }
     * }
     * <p>
     * public int findGcd(int a, int b){
     * return (a == 0 || b == 0) ? a + b : findGcd(b, a % b);
     * }
     * }
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        //Recursion
        /*if (null != nums && nums.length != 1 && k != 0) {
            int num = nums[0];
            nums[0] = nums[nums.length - 1];
            System.arraycopy(nums, 1, nums, 2, nums.length - 1 - 1);
            nums[1] = num;
            rotate(nums, k - 1);
        }*/

        //Iterative rotation
        /*int length = nums.length;
        int count = 0;
        int temp;
        int preValue;
        int current;
        for (int i = 0, next; count < length - 1; i++) {
            current = i;
            preValue = nums[i];
            do {
                next = (current + k) % length;
                temp = nums[next];
                nums[next] = preValue;
                preValue = temp;
                current = next;
                count++;
            } while (current != i);
        }*/

        //reverse
        int length = nums.length;
        k = k % length;
        reverse(nums, 0, length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        int temp;
        while (start < end) {
            temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1};
        new RotateArray().rotate(nums, 2);
        System.out.println(Arrays.toString(nums));
    }
}
