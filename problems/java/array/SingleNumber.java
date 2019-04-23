package array;

import java.util.*;

/**
 * @author BlackSugar
 * @date 2019/4/23
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
 * <p>
 * Note:
 * <p>
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * <p>
 * Example 1:
 * <p>
 * Input: [2,2,1]
 * Output: 1
 * Example 2:
 * <p>
 * Input: [4,1,2,1,2]
 * Output: 4
 */
public class SingleNumber {
    /**
     * 找出单个数字
     * 思路：
     * 1、正常来说就是遍历，利用Map，List，或者Set的不重复性，但是用了额外的空间
     * 2、之前见过类似的问题，可以使用异或运算，0^x=x x^x=0，可以推广到找出偶数个当中奇数个的数字
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        /*List<Integer> list = new ArrayList<>(nums.length);
        for (int num : nums) {
            if (list.contains(num)) {
                list.remove(Integer.valueOf(num));
            } else {
                list.add(num);
            }
        }
        return list.get(0);*/

        int re = 0;
        for (int num : nums) {
            re ^= num;
        }
        return re;
    }


    public static void main(String[] args) {
        System.out.println(new SingleNumber().singleNumber(new int[]{4, 1, 2, 1, 2}));
    }
}
