import java.util.Arrays;
import java.util.HashMap;

/**
 * @author wangwenjie
 * @date 2019/4/15
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * Example:
 * <p>
 * Given nums = [2, 7, 11, 15], target = 9,
 * <p>
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class TwoSum {
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            //O(nlogn)
            /*for (int i = 0; i < nums.length - 1; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] + nums[j] == target) {
                        return new int[]{i, j};
                    }
                }
            }
            System.out.println("not found");
            return null;*/

            //O(n)
            HashMap<Integer, Integer> map = new HashMap<>(tableSizeFor(nums.length * 2));
            for (int i = 0; i < nums.length; i++) {
                if (map.get(nums[i]) != null) {
                    return new int[]{map.get(nums[i]), i};
                }
                map.put(target - nums[i], i);
            }
            return null;

        }

        private final int tableSizeFor(int cap) {
            int n = cap - 1;
            n |= n >>> 1;
            n |= n >>> 2;
            n |= n >>> 4;
            n |= n >>> 8;
            n |= n >>> 16;
            return (n < 0) ? 1 : n + 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new TwoSum().new Solution().twoSum(new int[]{2, 7, 11, 15}, 9)));
    }
}
