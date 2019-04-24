package list;

/**
 * @author BlackSugar
 * @date 2019/4/16
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 */
public class MergeTwoSortedLists {
    /**
     * 合并两个递增链表
     * 思路：创建一条新链表，以l1、l2小的为节点，小节点的链表获取后一个节点再比较，若其中一条链表结束，则直接将另一条链表剩余节点加入
     * l1：123；l2:234--->122334
     * 一般思路：新建一条链表，以l1、l2小的节点的值创建新节点，直到l1、l2都为空
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode re = null, listNode = null;
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        do {
            if (l1.val < l2.val) {
                if (listNode == null) {
                    re = listNode = l1;
                } else {
                    listNode = listNode.next = l1;
                }
                l1 = l1.next;
            } else {
                if (listNode == null) {
                    re = listNode = l2;
                } else {
                    listNode = listNode.next = l2;
                }
                l2 = l2.next;
            }
        } while (l1 != null && l2 != null);
        if (l1 == null) {
            listNode.next = l2;
        }
        if (l2 == null) {
            listNode.next = l1;
        }
        return re;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next(3).next(4);
        ListNode l2 = new ListNode(2);
        l2.next(4).next(8);

        ListNode re = new MergeTwoSortedLists().mergeTwoLists(l1, l2);
        while (re != null) {
            System.out.print(re.val);
            re = re.next;
        }
        System.out.println();
    }
}
