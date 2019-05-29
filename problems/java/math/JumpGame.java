package math;

/**
 * @author BlackSugar
 * @date 2019/5/29
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Determine if you are able to reach the last index.
 * <p>
 * Example 1:
 * <p>
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 * <p>
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 * jump length is 0, which makes it impossible to reach the last index.
 */
public class JumpGame {
    /**
     * 跳跃游戏
     * 思路：
     * 1、标准的DP算法问题，对每步的数字进行循环作为下一步的初始位置然后递归，明显当数组大，数字大的时候效率太低 O(2^n)
     * 2、因为可以选择比当前数字小的步数，所以唯一断掉的可能和0有明显的关系，关键在于能不能跨过0，
     * 所以0前面的数字如果不大于和最后一个0之间的跨度，则不可能跨过
     * [1,0,4]--->1:index(0),0:index(1)--->1-0 <= 1--->false
     * ①、要么从最后一个0遍历到最开始的数字，遇到true就截断数组，再找最后一个0再次遍历。。。这里不写了
     * ②、要么我们可以记录最大跨度，由最大值每进一步减一再和当前值比较，如果到0的时候最大跨度 > 0，则可以通过这个0
     * <p>
     * 其他思路：算是上面方法的变种，从后往前推
     * public boolean canJump(int[] nums) {
     *         int lastPos = nums.length - 1;
     *         for (int i = nums.length - 1; i >= 0; i--) {
     *             if (i + nums[i] >= lastPos) {
     *                 lastPos = i;
     *             }
     *         }
     *         return lastPos == 0;
     *     }
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        /*return jumpHelper(0, nums);*/
        if (nums.length == 1) {
            return true;
        }
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max - 1, nums[i]);
            if (nums[i] == 0 && max <= 0 && i != nums.length - 1) {
                return false;
            }
        }
        return true;
    }

    private boolean jumpHelper(int start, int[] nums) {
        if (start == nums.length - 1) {
            return true;
        }
        for (int i = 1; i <= nums[start]; i++) {
            if (jumpHelper(start + i, nums)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new JumpGame().canJump(new int[]{3, 2, 1, 0, 4}));
    }
}
