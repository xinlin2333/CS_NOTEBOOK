/*
 * @lc app=leetcode id=146 lang=java
 *
 * [146] LRU Cache
 */

// @lc code=start

import java.util.HashMap;

class LRUCache {
    private class Node {
        int key;
        int val;
        Node pre;
        Node next;
        Node (int key, int val) {
            key = key;
            val = val;
        }
    }
    private final int capacity;
    private final Node dummy = new Node(0);
    private final Map<Integer, Node> keyToNode = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        dummy.next = dummy;
        dummy.pre = dummy;
    }

    public int get(int key) {
        Node node = keyToNode.get(key);
        if (node == null) {
            return -1;
        }
        return node.val;
    }
    public void put(int key, int value) {
        Node node = keyToNode.get(key);
        if(node != null) {
            node.val = value;
            return;
        }
        if(keyToNode.size() == capacity) {
           // move back()
              
        }
        // insert To keyToNode
        // Front 


    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
// @lc code=end

