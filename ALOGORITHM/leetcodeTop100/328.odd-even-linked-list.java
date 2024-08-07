/*
 * @lc app=leetcode id=328 lang=java
 *
 * [328] Odd Even Linked List
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
    public ListNode oddEvenList(ListNode head) {
       if(head==null) {
        return null;
       }
       ListNode evenHead = head.next;
       ListNode odd = head;
       ListNode even = evenHead;
       while(odd !=null && even !=null && even.next !=null) {
        odd.next = even.next;
        odd = odd.next;
        even.next = odd.next;
        even = even.next;
       }
       odd.next = evenHead;
       return head;
       
    }
}
// @lc code=end

