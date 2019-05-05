package list;

/**
 * @author BlackSugar
 * @date 2019/4/30
 * Reverse a singly linked list.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 * <p>
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class ReverseLinkedList {
    /**
     * 翻转链表
     * 思路：既然题目都要求用迭代和递归两种方式了，
     * 并且每种方式都可以有赋值给新链表法，O(n^2)，此处不写
     * 交换元素大法，O(nlogn)
     * 和LinkedListCycle一样的指针逆序法 O(n)，这里一样来一个
     * 1、递归，交换元素大法
     * 2、迭代，逆序法
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        /*ListNode end = head;
        int length = 1;
        while (end.next != null) {
            end = end.next;
            length++;
        }
        reverseListHelper(head, length);
        return head;*/
        ListNode pre = null, current = head, next = null;
        while (current != null) {
            next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        return pre;
    }

    private void reverseListHelper(ListNode head, int length) {
        ListNode end = head;
        for (int i = 1; i < length; i++) {
            end = end.next;
        }
        if (length != 1 && length != 0) {
            head.val ^= end.val;
            end.val ^= head.val;
            head.val ^= end.val;
            reverseListHelper(head.next, length - 2);
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next(2).next(3).next(4).next(5).next(6).next(7);
        ListNode re = new ReverseLinkedList().reverseList(l1);

        while (re != null) {
            System.out.print(re.val);
            re = re.next;
        }
        System.out.println();
    }
}
