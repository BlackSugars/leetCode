package array;

import java.util.Arrays;

/**
 * @author BlackSugar
 * @date 2019/4/25
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * <p>
 * You may assume that the array is non-empty and the majority element always exist in the array.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,2,3]
 * Output: 3
 * Example 2:
 * <p>
 * Input: [2,2,1,1,1,2,2]
 * Output: 2
 */
public class MajorityElement {
    /**
     * 找出主要元素
     * 思路：
     * 1、最简单的思路就是遍历，把数据放到map，每次加一之类的，这里就不考虑了，是因为懒 :)
     * 2、排序，然后找中位数，因为它大于一半
     * 大神思路：
     * Boyer-Moore Voting Algorithm：投票法，因为主要元素大于一半，我们设置是主要元素+1，非主要元素-1，则最后count>0的就是主要元素
     * public int majorityElement(int[] nums) {
     *         int count = 0;
     *         Integer candidate = null;
     *
     *         for (int num : nums) {
     *             if (count == 0) {
     *                 candidate = num;
     *             }
     *             count += (num == candidate) ? 1 : -1;
     *         }
     *
     *         return candidate;
     *     }
     * 其他还有归并什么的，就不提了
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public static void main(String[] args) {
        System.out.println(new MajorityElement().majorityElement(new int[]{3,2,3}));
    }
}
