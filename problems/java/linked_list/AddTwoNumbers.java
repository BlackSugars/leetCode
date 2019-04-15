package linked_list;

/**
 * @author BlackSugar
 * @date 2019/4/15
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example:
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class AddTwoNumbers {
    /**
     * 其实就是链表加法，都是倒序无影响
     * <p>
     * 思路：以l1为基准，一位一位相加，若进位则l1全部进位，直到其中一条为空，
     * ---若l1为空，则将l2剩余部分接入l1，若l2为空，则无需处理
     * <p>
     * 一般思路：新建一条链表，将相加值放入新链表对应节点，直到两条链表都为空，
     * ---新建链表的话就不需要先递归进位了，直接使用bool变量保存是否进位即可
     *
     * @param l1 链表1
     * @param l2 链表2
     * @return 新链表
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode re = l1;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val;
            if (sum > 9) {
                l1.val = sum % 10;
                carry(l1);
            } else {
                l1.val = sum;
            }
            if (l1.next == null && l2.next != null) {
                l1.next = l2.next;
                break;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        return re;
    }

    /**
     * carry add
     *
     * @param listNode
     */
    private void carry(ListNode listNode) {
        if (listNode.next != null) {
            listNode.next.val++;
            if (listNode.next.val > 9) {
                listNode.next.val = 0;
                carry(listNode.next);
            }
        } else {
            listNode.next = new ListNode(1);
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next(4).next(3);
        ListNode l2 = new ListNode(5);
        l2.next(6).next(4);

        ListNode re = new AddTwoNumbers().addTwoNumbers(l1, l2);
        while (re != null) {
            System.out.print(re.val);
            re = re.next;
        }
    }
}
