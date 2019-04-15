package array;

/**
 * @author BlackSugar
 * @date 2019/4/15
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * <p>
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * <p>
 * You may assume nums1 and nums2 cannot be both empty.
 * <p>
 * Example 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * The median is 2.0
 * Example 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * The median is (2 + 3)/2 = 2.5
 */
public class FindMedianSortedArrays {
    /**
     * 思路：新建一个double数组，对两个数组的值进行比较，小的放入新数组当中，
     * ---直到新数组的长度达到原来两个数组长度的一半（奇数为n+1），再根据奇偶性获得中位数
     * ps：因为我们所需的只有最后一两个数字，所以可以直接用下标来求值，不需要新建数组保存
     * <p>
     * 大神思路：由于中位数代表了把数组分为长度相等的两段，并且一边最大值小于等于另一边最小值
     *       left_part          |        right_part
     * A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
     * B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
     * len(left_part)=len(right_part)
     * max(left_part)≤min(right_part)
     * 用二分法求得这两个值
     *
     * @param nums1 数组1
     * @param nums2 数组2
     * @return median
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        int median = (length & 1) == 0 ? length / 2 : length / 2 + 1;
        int count = 0;
        double[] array = new double[length];
        if (length == 1) {
            return nums1.length == 0 ? nums2[0] : nums1[0];
        }
        for (int i = 0, j = 0; count <= median; count++) {
            if (i >= nums1.length) {
                array[count] = nums2[j];
                j++;
                continue;
            }
            if (j >= nums2.length) {
                array[count] = nums1[i];
                i++;
                continue;
            }
            if (nums1[i] <= nums2[j]) {
                array[count] = nums1[i];
                i++;
            } else {
                array[count] = nums2[j];
                j++;
            }
        }
        return (length & 1) == 0 ? (array[count - 1] + array[count - 2]) / 2 : array[count - 2];

        //todo 二分法
    }
}
