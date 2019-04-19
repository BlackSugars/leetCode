package array;

import java.util.Arrays;

/**
 * @author BlackSugar
 * @date 2019/4/19
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * <p>
 * Note:
 * <p>
 * 1、The number of elements initialized in nums1 and nums2 are m and n respectively.
 * 2、You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
 * Example:
 * <p>
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * Output: [1,2,2,3,5,6]
 */
public class MergeSortedArray {
    /**
     * 合并有序数组
     * 思路：
     * 1、直接放进去排序
     * 2、因为是有序数组，比较两个最大值，把大的那个放到数组1最后，大的数字所在数组索引向左移一位再次比较
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        while (m > 0 && n > 0) {
            nums1[m + n - 1] = nums1[m - 1] > nums2[n - 1] ? nums1[m-- - 1] : nums2[n-- - 1];
        }
        if (m == 0) {
            System.arraycopy(nums2, 0, nums1, 0, n);
        }
    }

    public static void main(String[] args) {
        int[] num = new int[]{2, 0};
        new MergeSortedArray().merge(num, 1, new int[]{1}, 1);
        System.out.println(Arrays.toString(num));
    }
}
