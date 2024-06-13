/*
 * @lc app=leetcode id=234 lang=java
 *
 * [234] Palindrome Linked List
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
    public boolean isPalindrome(ListNode head) {
        if (head ==null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode second = reverse(slow);
        ListNode first  = head;
        while(second!= null) {
            if (second.val != first.val) {
                return false;
            }
            first = first.next;
            second = second.next;
        }
        return true;
        
    }
    public ListNode reverse(ListNode node) {
  
        ListNode pre = null;
        ListNode cur = node;
        while(cur!=null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }
}
// @lc code=end

