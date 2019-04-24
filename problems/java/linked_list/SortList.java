package linked_list;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author BlackSugar
 * @date 2019/4/23
 * Sort a linked list in O(n log n) time using constant space complexity.
 * <p>
 * Example 1:
 * <p>
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * Example 2:
 * <p>
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 */
public class SortList {
    /**
     * 链表排序
     * 思路：
     * 1、如果输入范围不大的话可以使用桶排序，时间O(n+m)，空间O(n+m)
     * 2、比较排序：
     * ①、归并，用两个指针，一个快，一个慢，找出链表的终点并分为两个链表，然后类似于MergeTwoSortedLists，时间达到O(nlogn)，但是空间是O(logn)，
     * 这里可以使用自下而上的归并，这样空间就是O(1)了，难点在于如何分成子链表，先找到长度，然后按2^n生成子链表，直到2^n大于总长度
     * At each level, each group only contains at maximum 2^level elements. Merge-sort theses groups pair by pair. Then level ++. Stop until 2^level > n.
     * Assume the original input is :
     * level 0        5, 3, 6, 1, 4, 2, 7
     * After level 0, we got the length of the list and the list become:
     * level 1        3, 5,   1, 6,    2, 4,    7
     * Now each group contains 2 elements. After level 1, the list become:
     * level 2        1, 3, 5, 6,    2, 4, 7
     * Now each group contains 2^2 = 4 elements. After level 2, the list become:
     * level 3        1, 2, 3, 4, 5, 6, 7
     * Now, 2^3 > 7, stop.
     * ②、快排，以第一个元素为基准，比他大的元素放进一个list，小于等于他的元素放进另一个list，重复操作，空间O(n)或者O(logn)
     * 3、奇葩方法，遍历进list，利用list排序
     */
    /**
     * up-to-bottom merge sort
     *
     * @param head
     * @return
     */
    /*public ListNode sortList(ListNode head) {
        //base case
        if (null == head || head.next == null) {
            return head;
        }
        //divide into two lists
        ListNode pre = head, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(slow);

        //merge two sorted lists
        ListNode merge = new ListNode(0), re = merge;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                merge.next = left;
                left = left.next;
            } else {
                merge.next = right;
                right = right.next;
            }
            merge = merge.next;
        }
        merge.next = left == null ? right : left;
        return re.next;
    }*/

    /**
     * Bottom-to-up(not recurring), iteration merge sort, space O(1)
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        //base case
        if (null == head || head.next == null) {
            return head;
        }
        ListNode count = head;
        int length = 1;
        //get length
        while (count.next != null) {
            length++;
            count = count.next;
        }
        //i is level, n is the length of each segment,1,2,4,8...
        for (int i = 1, n = 1; n <= length; i++, n = 1 << (i - 1)) {
            //Initialize list by new head
            ListNode dummyHead = head;
            ListNode mergeList = new ListNode(0), newHead = mergeList;
            //Iterator dummy head to get left and right lists by n
            //if n=1 [left,right]==([5], [3]), ([6], [1]), ([4], [2]), ([7], [null])
            //if n=2 [left,right]==([3, 5], [1, 6]), ([2, 4], [7])
            //...
            while (dummyHead != null) {
                ListNode left = new ListNode(0), right = new ListNode(0),
                        dummyLeft = left, dummyRight = right;
                for (int j = 0; j < n && dummyHead != null; j++) {
                    left.next = dummyHead;
                    left = left.next;
                    dummyHead = dummyHead.next;
                }
                for (int j = 0; j < n && dummyHead != null; j++) {
                    right.next = dummyHead;
                    right = right.next;
                    dummyHead = dummyHead.next;
                }
                //truncated list, preparation for sorting
                left.next = right.next = null;
                left = dummyLeft.next;
                right = dummyRight.next;
                //get mergeList's end
                while (mergeList.next != null) {
                    mergeList = mergeList.next;
                }
                //just insert, like MergeTwoSortedList
                while (left != null && right != null) {
                    if (left.val <= right.val) {
                        mergeList.next = left;
                        left = left.next;
                    } else {
                        mergeList.next = right;
                        right = right.next;
                    }
                    mergeList = mergeList.next;
                }
                mergeList.next = left == null ? right : left;
            }
            //Initialize new head
            head = newHead.next;
        }
        return head;
    }

    /**
     * quick sort
     *
     * @param head
     * @return
     */
    /*public ListNode sortList(ListNode head) {
        //base case
        if (null == head || head.next == null) {
            return head;
        }
        ListNode sentinel = head, less = new ListNode(0), more = new ListNode(0),
                dummyLess = less, dummyMore = more, mid = sentinel;
        head = head.next;
        while (head != null) {
            if (head.val < sentinel.val) {
                less.next = head;
                less = less.next;
            } else if (head.val > sentinel.val) {
                more.next = head;
                more = more.next;
            } else {
                sentinel.next = head;
                sentinel = sentinel.next;
            }
            head = head.next;
        }

        less.next = more.next = null;
        ListNode re = dummyLess;
        dummyLess.next = sortList(dummyLess.next);
        while (dummyLess.next != null) {
            dummyLess = dummyLess.next;
        }
        //left connect mid
        dummyLess.next = mid;

        //connect right
        sentinel.next = sortList(dummyMore.next);
        return re.next;
    }*/

    /**
     * use list,just for fun :)
     *
     * @param head
     * @return
     */
    /*public ListNode sortList(ListNode head) {
        //base case
        if (null == head || head.next == null) {
            return head;
        }
        final ListNode[] a = {head};
        ListNode re = a[0];
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        list.sort(Comparator.comparingInt(o -> o));
        list.forEach(num -> {
            a[0].val = num;
            a[0] = a[0].next;
        });
        return re;
    }*/

    /**
     * main
     */
    public static void main(String[] args) {
        ListNode l1 = new ListNode(-4);
        l1.next(5).next(1).next(3).next(0);
        ListNode re = new SortList().sortList(l1);
        while (re != null) {
            System.out.print(re.val);
            re = re.next;
        }
        System.out.println();
    }
}
