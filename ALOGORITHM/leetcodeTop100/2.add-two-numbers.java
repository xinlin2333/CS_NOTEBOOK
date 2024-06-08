/*
 * @lc app=leetcode id=2 lang=java
 *
 * [2] Add Two Numbers
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwo(l1,l2,0);

    }

    private ListNode addTwo(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null) {
            return carry ==0 ? null: new ListNode(carry);
        }
        if (l1 == null) {
            l1 = l2;
            l2 = null;
        }
        carry = (l1.val + (l2== null?0:l2.val) + carry) ;
        l1.val = carry %10;
        l1.next = addTwo(l1.next, (l2==null?null:l2.next), carry/10);
        return l1;
    }
}
// @lc code=end

