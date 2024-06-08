/*
 * @lc app=leetcode id=138 lang=java
 *
 * [138] Copy List with Random Pointer
 */

// @lc code=start
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

import org.w3c.dom.Node;

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        Map<Node, Node>  map = new HashMap<>();
        while(cur!=null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while(cur!= null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        //map as deepCopy obj 
        return map.get(head);
    }
}
// @lc code=end

