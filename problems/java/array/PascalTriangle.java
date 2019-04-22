package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BlackSugar
 * @date 2019/4/22
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 * <p>
 * <p>
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 * <p>
 * Example:
 * Input: 5
 * Output:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 */
public class PascalTriangle {
    /**
     * 帕斯卡三角
     * 思路：和String类的CountAndSay差不多，递归和迭代两种方式
     * 1、迭代，每层将上一层的数据取出，根据规律生成元素并依次添加进一个新list，再放入总lists当中，直到目标层数，1ms
     * 优化：利用set，创建新list的时候将上一个list数据直接复制，然后根据规律对应修改中间位数的数字，最后再加一个1即可，少了add操作，0ms
     * 2、递归，递归出numRows-1行的数据，再进行处理，生成numRows行的数据返回，保存在list之中，1ms
     * 优化：单纯递归每次都需要重复生成1~n行的数据，消耗资源，我们直接递归一次最大层数据，每层用map缓存一下，然后直接取map即可
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists = new ArrayList<>();
        if (numRows == 0) {
            return lists;
        }
        //Iteration
        /*List<Integer> list = new ArrayList<>();
        list.add(1);
        lists.add(list);
        for (int i = 1; i < numRows; i++) {
            List<Integer> preList = lists.get(i - 1);
            List<Integer> currentList = new ArrayList<>(preList);
            for (int j = 1; j < preList.size(); j++) {
                currentList.set(j, preList.get(j - 1) + preList.get(j));
            }
            currentList.add(1);
            lists.add(currentList);
        }
        return lists;*/

        //Recusion
        Map<Integer, List<Integer>> map = new HashMap<>(numRows);
        generateHelper(numRows, map);
        for (int i = 1; i <= numRows; i++) {
            lists.add(map.get(i));
        }
        return lists;
    }

    private Map<Integer, List<Integer>> generateHelper(int numRows, Map<Integer, List<Integer>> map) {
        if (numRows == 0) {
            map.put(0, new ArrayList<>());
            return map;
        }
        List<Integer> preList = generateHelper(numRows - 1, map).get(numRows - 1);
        List<Integer> currentList = new ArrayList<>(preList);
        for (int j = 1; j < preList.size(); j++) {
            currentList.set(j, preList.get(j - 1) + preList.get(j));
        }
        currentList.add(1);
        map.put(numRows, currentList);
        return map;
    }


    public static void main(String[] args) {
        System.out.println(new PascalTriangle().generate(5));
    }
}
