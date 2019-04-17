package array;


/**
 * @author BlackSugar
 * @date 2019/4/16
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * <p>
 * Example:
 * <p>
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Follow up:
 * <p>
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class MaximumSubarray {
    /**
     * 获取具有最大值的子数组
     * 思路：已经说了用分治的方法，我们先实现O(n)的方法
     * 1、首先肯定就是迭代走起，如果最复杂的肯定是双循环，不过这个复杂度达到了O(n^2)，不符合要求
     * 2、获取最大值，肯定是要在两个正数当中产生，找到第一个正数为起始迭代，若加起来为负数，则sum归位，然后取最大值
     * 3、分治法，感觉还没有DP方便，将值递归拆分为一个个左右小块，再依次合并起来，期间比较左右最大值或者两者之间有最大值, O(nlogn)
     * [-2,1,-3,4,-1,2,1,-5,4]--->[-2,1,-3,4,-1]+[2,1,-5,4]--->[-2,1,-3]+[4,-1] + [2,1]+[-5,4]
     * --->[-2,1]+[-3] + [4]+[-1]...
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        /*int sum = 0, max = nums[0];
        for (int num : nums) {
            sum = sum > 0 ? sum + num : num;
            max = Math.max(sum, max);
        }*/

        //分治法

        int max = maxSubArrayHelper(nums, 0, nums.length - 1);

        return max;
    }

    private int maxSubArrayHelper(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        int mid = (end + start) / 2;
        int sum = 0, max;

        int left = maxSubArrayHelper(nums, start, mid);
        int right = maxSubArrayHelper(nums, mid + 1, end);
        max = Math.max(left, right);

        int localMax1 = Integer.MIN_VALUE;
        for (int i = mid; i >= start; i--) {
            sum += nums[i];
            localMax1 = Math.max(sum, localMax1);
        }

        int localMax2 = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= end; i++) {
            sum += nums[i];
            localMax2 = Math.max(sum, localMax2);
        }
        return Math.max(localMax1 + localMax2, max);
    }

    public static void main(String[] args) {
        System.out.println(new MaximumSubarray().maxSubArray(new int[]{-2, 1, -3, 4, -1, -2, 1, -5, 4}));
    }
}
