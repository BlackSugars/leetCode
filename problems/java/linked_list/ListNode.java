package linked_list;

/**
 * @author BlackSugar
 * @date 2019/4/15
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    /**
     * 方便添加节点
     *
     * @param val value
     * @return ListNode
     */
    public ListNode next(int val) {
        this.next = new ListNode(val);
        return this.next;
    }
}
