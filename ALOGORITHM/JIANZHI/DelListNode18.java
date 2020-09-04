package ALOGORITHM.JIANZHI;

import java.util.List;

/**
 * @author canoeYang
 * @Date 2020-08-30 18:27
 */
public class DelListNode18 {
    public ListNode deleteNode(ListNode head, int val) {
        if(head==null){
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = dummy;
        while(p.next!=null){
            if(p.next.val==val){
                p.next = p.next.next;
                break;
            }
            p=p.next;
        }
        return dummy.next;
    }
}
class ListNode{
    int val;
    ListNode next;
    ListNode(int x) {val = x;};
}
