/*
 * @lc app=leetcode id=21 lang=java
 *
 * [21] Merge Two Sorted Lists
 */

// @lc code=start

import javax.management.ListenerNotFoundException;

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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) {
            return null;
        }
        if (list1 == null ) {
            return list2;
        }
        if (list2 == null ){
            return list1;
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (list1 != null && list2 != null) {
            if (list1.value < list2.value) {
                cur.next = list1;
                list1 = list1.next;
            }else{
                cur.next = list2 ;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        while(l.next != null) {
            cur = l;
            l = l.next;
            cur = cur.next;
        }
        while(r.next != null) {
            cur = r;
            r = r.next;
            cur = cur.next;
        }
        return res;
    }
}
// @lc code=end

