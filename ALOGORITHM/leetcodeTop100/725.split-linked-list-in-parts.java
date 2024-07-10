/*
 * @lc app=leetcode id=725 lang=java
 *
 * [725] Split Linked List in Parts
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode[] splitListToParts(ListNode head, int k) {
        int count =0 ;
        ListNode cur = head;
        while (cur!=null) {
            count++;
            cur = cur.next;
        }
        ListNode[] list = new ListNode[k];
        int seg = count / k;
        int remainder = count % k;
        for (int i=0; i< k && cur!= null;i++) {
            list[i] = cur;
            int size =  seg + (i<remainder?1:0);
            for (int j=1;j<size;j++) {
                cur = cur.next;
            }
            ListNode next = cur.next;
            cur.next = null;
            cur = next;
        }
        return list;
        
    }
}
// @lc code=end

