/*
 * @lc app=leetcode id=86 lang=java
 *
 * [86] Partition List
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

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
    public ListNode partition(ListNode head, int x) {
        if(head == null) {
            return head;
        } 
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        ListNode cur = head;
        while(cur != null) {
            if (cur.val < x) {
                l1.append(cur.val);
            }else{
                l2.append(cur.val);
                
            }
            cur = cur.next;
        }
        cur = head;
        int i = 0;
        int j = 0;
        while(cur!=null&& i<l1.size()) {
                cur.val = l1.get(i);
                i++;
                cur = cur.next;    
        }
        while(cur!=null&& j<l2.size()) {
            cur.val = l2.get(j);
            j++;
            cur = cur.next;    
        }
        return head;
    }
}
// @lc code=end

