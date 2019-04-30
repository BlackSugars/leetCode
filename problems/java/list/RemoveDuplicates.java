package list;

/**
 * @author BlackSugar
 * @date 2019/4/18
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->1->2
 * Output: 1->2
 * Example 2:
 * <p>
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 */
public class RemoveDuplicates {
    /**
     * 移除list重复元素
     * 思路：
     * 1、迭代，遇到重复元素则将next改为next.next，set保存判断重复数字
     * 2、因为题目说了排过序的数列，所以只要val！=next.val就代表不是重复的，不需要set保存
     * 3、还可以用递归
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (null == head) {
            return null;
        }
        /*ListNode re = head;
        while (head.next != null) {
            if (head.val != head.next.val) {
                head = head.next;
            } else {
                head.next = head.next.next;
            }
        }
        return re;*/

        if (head.next == null) {
            return head;
        }
        head.next = deleteDuplicates(head.next);

        return head.val == head.next.val ? head.next : head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next(3).next(4).next(4);
        ListNode re = new RemoveDuplicates().deleteDuplicates(l1);
        while (re != null) {
            System.out.print(re.val);
            re = re.next;
        }
        System.out.println();
    }
}
