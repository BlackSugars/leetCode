package array;

import java.util.Arrays;

/**
 * @author BlackSugar
 * @date 2019/5/14
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 * <p>
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class KthLargestElement {
    /**
     * 获取列表当中地k大的元素
     * 思路：
     * 1、直白一点就是直接遍历，取最大值，并保存最大值索引，然后剔除出去再次遍历，此处不写 O(n^2)
     * 2、先从大到小排序，然后直接取，排序就有快排、分治等等 O(nlongn)
     * ①、分治，将数组分为左右两部分，递归直到数据只有一个元素，然后再进行比较排序，类似MergeSortedArray，这里使用传递索引的方法避免生成实际的左右数组。
     * ②、快排，数组天生就是快排的料，这里直接使用标准的快排技巧分成大小两部分(或者你可以自己建大小数组，不过这样空间开销就大了)，
     * 两个指针，左边找到比他大的，右边找到比他小的，交换位置继续直到指针相遇
     * 因为快排是从一半一半排好序的，所以可以优化一下，看k在什么位置，然后排到k位就不排了
     * 3、写着玩，直接用 Arrays.sort :)
     * 大神思路:
     * 虽然也是快排，但是更优秀 :)
     * public int findKthLargest(int[] nums, int k) {
     *         int n = nums.length;
     *         int target = n-k;
     *         quickSort(nums,0,n-1,target);
     *         return nums[n-k];
     *     }
     *
     *
     *     public void quickSort(int[] nums, int left, int right, int target){
     *         if(left>=right)
     *             return;
     *
     *         int center = (left + right)/2;
     *         selectPivot(nums, left, right);
     *         int pivot = nums[center];
     *         int i = left;
     *         int j = right;
     *
     *         while(i<=j){
     *             while(nums[i] < pivot){
     *                 i++;
     *             }
     *             while(nums[j] > pivot){
     *                 j--;
     *             }
     *             if(i<=j){
     *                 if(nums[i]!=nums[j])
     *                     swap(nums,i,j);
     *                 i++;
     *                 j--;
     *             }
     *         }
     *
     *         if(target<=i-1)
     *             quickSort(nums, left, i-1,target);
     *         else
     *             quickSort(nums, i, right, target);
     *     }
     *
     *
     *     public void selectPivot(int[] nums, int left, int right){
     *         int center = (left +right)/2;
     *         if(nums[left] > nums[center])
     *             swap(nums, left, center);
     *         if(nums[left] > nums[right])
     *             swap(nums, left, right);
     *         if(nums[center] > nums[right])
     *             swap(nums, center, right);
     *     }
     *
     *     public void swap(int[] nums, int i, int j){
     *         int tmp = nums[i];
     *         nums[i] = nums[j];
     *         nums[j] = tmp;
     *     }
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {

        /*return findKthLargestHelper(nums, 0, nums.length - 1)[k - 1];*/

        /*Arrays.sort(nums);
        return nums[nums.length - k];*/

        quickFindKthLargestHelper(nums, 0, nums.length - 1, k);
        return nums[nums.length - k];
    }

    private int[] findKthLargestHelper(int[] nums, int start, int end) {
        if (end == start) {
            return new int[]{nums[start]};
        }
        int mid = (end + start) / 2;
        int[] left = findKthLargestHelper(nums, start, mid);
        int[] right = findKthLargestHelper(nums, mid + 1, end);
        int lLength = left.length, rLength = right.length, n = 0, m = 0;
        int[] re = new int[lLength + rLength];
        while (n != lLength && m != rLength) {
            re[m + n] = left[n] > right[m] ? left[n++] : right[m++];
        }
        if (n == lLength) {
            System.arraycopy(right, m, re, m + n, rLength - m);
        } else {
            System.arraycopy(left, n, re, m + n, lLength - n);
        }
        return re;
    }

    private void quickFindKthLargestHelper(int[] nums, int start, int end, int pos) {
        if (start >= end) {
            return;
        }
        int i = start + 1, j = end;
        while (i <= j) {
            while (i <= j && nums[i] <= nums[start]) {
                i++;
            }
            while (i <= j && nums[j] >= nums[start]) {
                j--;
            }
            if (i < j) {
                swap(nums, i++, j--);
            }
        }
        if (start != j) {
            swap(nums, start, j);
        }
        if (j >= nums.length - pos) {
            quickFindKthLargestHelper(nums, start, j - 1, pos);
        } else {
            quickFindKthLargestHelper(nums, j + 1, end, pos);
        }
    }

    private void swap(int[] nums, int n, int m) {
        int temp = nums[m];
        nums[m] = nums[n];
        nums[n] = temp;
    }

    public static void main(String[] args) {
        System.out.println(new KthLargestElement().findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 9, 6}, 3));
    }
}
