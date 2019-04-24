package list;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author BlackSugar
 * @date 2019/4/24
 * Given a linked list, determine if it has a cycle in it.
 * To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
 * <p>
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 * 3--->2--->0--->4
 *      ^         |
 *      |         |
 *      <--- --- -v
 * <p>
 * Example 2:
 * Input: head = [1,2], pos = 0
 * Output: true
 * Explanation: There is a cycle in the linked list, where tail connects to the first node.
 * 1--->2
 * ^    |
 * |    |
 * <--- v
 * <p>
 * Example 3:
 * Input: head = [1], pos = -1
 * Output: false
 * Explanation: There is no cycle in the linked list.
 * <p>
 * Follow up:
 * Can you solve it using O(1) (i.e. constant) memory?
 */
public class LinkedListCycle {
    /**
     * 判断是否有环形链表
     * 思路：
     * 1、将对象保存进set、list、map，遍历链表，若有插入不进去即存在环形链表
     * 2、题目要求使用O(1)空间，我们设定一个快指针每次走两步，一个慢指针每次走一步，如果有循环，则快指针必会追到慢指针
     * 3、奇葩方法，猜测测试用例没有超过一万位，计数 :)
     * 4、其他大神方法：
     * ①、每过一个节点，将节点设置为自循环，如果有环形链表，则他会指回我们设置过的自循环节点，则node=node.next，但是这会破坏链表结构
     * public boolean hasCycle(ListNode head) {
     *         while (head != null) {
     *             if (head.next == head)
     *                 return true;
     *             ListNode tmp = head.next;
     *             head.next = head;
     *             head = tmp;
     *         }
     *         return false;
     *     }
     * 递归形式：
     * public boolean hasCycle(ListNode head){
     *        if(head == null || head.next == null) return false;
     *        if(head.next == head) return true;
     *        ListNode nextNode = head.next;
     *        head.next = head;
     *        boolean isCycle = hasCycle(nextNode);
     *        return isCycle;
     *    }
     * <p>
     * ②、每过一个节点，将节点的指针反向，若有环形链表，则会再次经过head节点，这也会破坏链表结构
     * public boolean hasCycle(ListNode head) {
     *         ListNode cur=head, pre=null, next=null;
     *         while(cur!=null){
     *             next = cur.next;
     *             cur.next = pre;
     *             pre = cur;
     *             cur = next;
     *             if(cur==head) return true;
     *         }
     *         return false;
     *     }
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        /*Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;*/

        if (null == head) {
            return false;
        }
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;

        /*int count = 0;
        while (head != null) {
            if (count > 10000) {
                return true;
            }
            count++;
            head = head.next;
        }
        return false;*/
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next(3).next(4);
        System.out.println(new LinkedListCycle().hasCycle(l1));
    }
}
