package ALOGORITHM.JIANZHI;

/**
 * @author canoeYang
 * @Date 2020-08-30 19:09
 */
public class ReverseListNode24 {
    public ListNode reverseList(ListNode head) {
        if(head == null){
            return head;
        }
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        ListNode cur =head;
        while(cur!=null){
            ListNode next = head.next;
            cur.next = p;
            p = head;
            cur = next;
        }
        return head;
    }
}
