/*
 * @lc app=leetcode id=146 lang=java
 *
 * [146] LRU Cache
 */

// @lc code=start

import java.util.HashMap;

class Node {
    int key;
    int value;
    Node pre;
    Node next;
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class DoubleList{
    private Node head;
    private Node tail;
    int capacity;

    // DoubleLinked == null->set Node as head,tail; 
    // insert New Node in the front (as the most recent used Node)
    public void addFront(Node node) {
        if (head == null) {
            head = tail = node;
        }else {
            node.next = head;
            head.pre = node;
            head = node;
        }
       capacity++;
    }
    // nil DoubleList; head == tail = null;
    // head remove; node.next.pre= null;head= node.next;
    // tail remove; node.prev.next = null;tail = node.pre;
    // internal remove: node.prev.next = node.next; node.next.prev = node.prev
    public void remove(Node node) {
        if (node == null) {
            return ; 
        }
        if (head == node && tail == node) {
            head = null;
            tail = null;
        }else if (tail == node) {
            if (node.pre != null) {
                node.pre.next= null;
            }
            tail = node.pre;
        }else if (head == node) {
            if (node.next != null) {
                node.next.pre = null;
            }
            head = node.next;
        }else {
            if (node.pre != null) {
                node.pre.next = node.next;
            }
            if (node.next != null) {
                node.next.pre = node.pre; 
            }
            
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

class LRUCache {
    public static void main(String[] args) {

    }
    /**
     * LRUCache：DoubleLinkedList+HashMap; 
     * HashMap: searchKey O(1); hashMap <key, Node) Node points to  DoubleLinkedList 
     */
    HashMap<Integer,Node> hm;
    DoubleList cache;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.hm = new HashMap<>();
        this.cache = new DoubleList(); 
    }
    /**
     * find key: nil return -1; getOne: movetoFront;
     * @param key
     * @return
     */
    public int get(int key) {
        if (!hm.containsKey(key)) {
            return -1;
        }
        int res = hm.get(key).value;
        put(key,res);
        return res;
    }
    /**
     * key exists, update the value; capacity full, removeLastOne One from cache and erase index; addFront Cache && update HashMap key, Node value
     * @param key
     * @param value
     */
    public void put(int key, int value) {
       // exist;
       if (hm.containsKey(key)) {
         Node node = hm.get(key);
         cache.remove(node);
         node.value = value;
         cache.addFront(node);
         hm.put(key, node);
       }else {
        // not exist but no capacity;
          if (cache.size() == capacity) {
            Node tmp = cache.removeLast();
            hm.remove(tmp.key);
          }
          Node newNode = new Node(key,value);
          cache.addFront(newNode);
          hm.put(key, newNode);
       } 
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
// @lc code=end

