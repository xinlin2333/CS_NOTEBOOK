package ALOGORITHM.JIANZHI;

/**
 * @author canoeYang
 * @Date 2020-08-30 18:47
 */
public class reciprocalKNum22 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        if(head == null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null&&k>0){
            fast=fast.next;
            k--;
        }
        if(fast==null){
            return null;
        }
        while(fast!=null){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;

    }
}

