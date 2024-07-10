/*
 * @lc app=leetcode id=2181 lang=java
 *
 * [2181] Merge Nodes in Between Zeros
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
    public ListNode mergeNodes(ListNode head) {
        ListNode ans = head;
        int sum = 0;
        ListNode cur = head.next;
        while(cur!=null){
            if(cur.val > 0) {
                sum += cur.val;
            }else{
                ans.next.val = sum;
                ans = ans.next;
                sum = 0;
            }
            cur = cur.next;
        }
        return ans;
    }
}
// @lc code=end

