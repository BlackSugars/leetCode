package array;

/**
 * @author BlackSugar
 * @date 2019/4/28
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * <p>
 * Example 1:
 * <p>
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 * <p>
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
public class MaximumProductSubarray {
    /**
     * 找出最大乘积的子串
     * 思路：和最大和子串类似，后悔打开这道题 T^T，注意乘法不像加法，最大乘积可以包含偶数个负数
     * 1、迭代
     * ①、首先肯定是双循环迭代，此处不写 O(n^2)
     * ②、同时记录最小和最大，每次将最小值和最大值与当前值相乘，并与当前值比较出最大值和最小值，若当前值比乘积大（小），则取当前值，结果与前面无关，
     * 类似加法的值小于0就取当前值 O(n) 2ms
     * 2、归并，同样注意最大子串有三种情况，左边，右边，中间，但是每次要返回最大和最小值，感觉有点麻烦，而且是O(nlogn)。。。
     * 大神思路：
     * 1、通过判断当前值是正还是负，若为负则交换最大值和最小值，这样可以减少两次比较 1ms
     * for(int i=1;i<nums.length;++i){
     *             if(nums[i]<0){
     *                 int temp = maxVal;
     *                 maxVal = minVal;
     *                 minVal = temp;
     *             }
     *             maxVal = Math.max(nums[i],maxVal*nums[i]);
     *             minVal = Math.min(nums[i],minVal*nums[i]);
     *             ans = Math.max(maxVal,ans);
     *         }
     * 2、（大神不愧是大神）正着来一次最大值，再倒着来一次最大值 0ms，
     * 因为乘法和加法不一样，乘法不会存在那种子串在中间的情况，如果两边都是负的，那么乘法是直接包含两边即可（偶数个负号），
     * 所以要么在前半子串，要么在后半子串（奇数个负号）
     * [-2, 3, -1, -2, 6, -4, -2, 8]---> [-2, 3, -1, -2, 6, -4] || [3, -1, -2, 6, -4, -2, 8]
     * public int maxProduct(int[] nums) {
     *         int max=nums[0], curMax=1;
     *         for(int i=0; i<nums.length; i++) {
     *             curMax*=nums[i];
     *             if(curMax>max) max=curMax;
     *             if(curMax==0) curMax=1;
     *         }
     *         curMax=1;
     *         for(int i=nums.length-1; i>=0; i--) {
     *             curMax*=nums[i];
     *             if(curMax>max) max=curMax;
     *             if(curMax==0) curMax=1;
     *         }
     *         return max;
     *     }
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        /*int max = nums[0], min = nums[0], totalMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int tempMax = nums[i] * max;
            int tempMin = nums[i] * min;
            max = Math.max(Math.max(tempMax, tempMin), nums[i]);
            min = Math.min(Math.min(tempMax, tempMin), nums[i]);
            totalMax = Math.max(totalMax, max);
        }
        return totalMax;*/
        int max = nums[0], product = 1, product2 = 1;
        for (int i = 0; i < nums.length; i++) {
            product *= nums[i];
            product2 *= nums[nums.length - 1 - i];
            max = Math.max(Math.max(max, product), product2);
            product = product == 0 ? 1 : product;
            product2 = product2 == 0 ? 1 : product2;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new MaximumProductSubarray().maxProduct(new int[]{-2, 3, -1, -2, 0, -4, -2, 8}));
    }
}
