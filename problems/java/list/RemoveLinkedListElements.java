package list;

/**
 * @author BlackSugar
 * @date 2019/4/30
 * Remove all elements from a linked list of integers that have value val.
 * <p>
 * Example:
 * <p>
 * Input:  1->2->6->3->4->5->6, val = 6
 * Output: 1->2->3->4->5
 */
public class RemoveLinkedListElements {
    /**
     * 删除list当中的元素
     * 思路：
     * 1、肯定还是遍历，时间O(n)，两种
     * ①、一种是新建一个头，遇到不是目标值的把它放到新头后面，目标值跳过，此处忽略
     * ②、一种是用双指针，pre指针遇到目标值就让它指向目标值后一位
     * 2、甚至你还可以转为list，用list封装好的方法 :)
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode dummyHead = head.next, pre = head;
        while (dummyHead != null) {
            if (dummyHead.val == val) {
                pre.next = dummyHead.next;
            } else {
                pre = pre.next;
            }
            dummyHead = dummyHead.next;
        }
        return head.val == val ? head.next : head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next(2).next(6).next(3).next(4).next(5).next(6);
        ListNode re = new RemoveLinkedListElements().removeElements(l1, 6);

        while (re != null) {
            System.out.print(re.val);
            re = re.next;
        }
        System.out.println();
    }
}
