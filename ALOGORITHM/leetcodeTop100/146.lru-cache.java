/*
 * @lc app=leetcode id=146 lang=java
 *
 * [146] LRU Cache
 */

// @lc code=start

import java.util.HashMap;

class LRUCache {
    HashMap<Integer,Node> hm;
    DoubleLinkedList cache;
    private int capacity;

    public LRUCache(int capacity) {
       this.capacity = capacity;
       this.cache = new DoubleLinkedList();
       this.hm = new HashMap<>();
    }

    public int get(int key) {
        if(!hm.containsKey(key)) {
            return -1;
        }
        int res = hm.get(key).value;
        put(key, res);
        return res;
    }

    public void put(int key, int value) {
        if(hm.containsKey(key)) {
            Node node = hm.get(key);
            cache.remove(node);
            node.value = value;
            cache.addFront(node);
            hm.put(key, node);
        }else{
            if(capacity == cache.size()) {
                Node lastNode  =  cache.removeLast();
                hm.remove(lastNode.key);
            }
            Node newNode = new Node(key, value);
            hm.put(key, newNode);
            cache.addFront(newNode);
        }

    }

}

class Node {
    int key;
    int value;
    Node pre;
    Node next;

    public Node (int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class DoubleLinkedList {
    int capacity;
    Node head;
    Node tail;
    public void addFront(Node node) {
        if (head == null) {
            head = tail = node;
        }else{
            node.next = head;
            head.pre = node;
            head = node;
        }
        capacity++;
    }
    public void remove(Node node) {
        if (node == null) {
            return ;
        }
        if(head == node && tail == node) {
            head = null;
            tail = null;
        }else if (tail == node) {
            node.pre.next = null;
            tail = node.pre;
        }else if(head == node) {
            node.next.pre = null;
            head = node.next;
        }else {
            node.next.pre = node.pre;
            node.pre.next = node.next;
        }
        capacity--;
     }
     public Node removeLast() {
        Node node = tail;
        remove(node);
        return node;
     }
     public int size() {
        return capacity;
     }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
// @lc code=end

