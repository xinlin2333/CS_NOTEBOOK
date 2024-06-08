/*
 * @lc app=leetcode id=61 lang=java
 *
 * [61] Rotate List
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
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || k ==0 ) {
            return head;
        } 
        int count = 1;
        ListNode cur = head;
        while(cur.next!=null) {
            count++;
            cur = cur.next;
        } 
        if(k % count == 0) {
            return head;
        } 
        k = k %count;
        cur.next = head;
        for (int i=0;i<(count - k);i++) {
            cur = cur.next;
        }
        head = cur.next;   
        cur.next= null;
        return head;
    }
}
// @lc code=end

