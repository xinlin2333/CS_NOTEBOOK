/*
 * @lc app=leetcode id=460 lang=java
 *
 * [460] LFU Cache
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;

class LFUCache {
    private static class Node {
        int key, value, fre = 1;
        Node pre;
        Node next;
        Node(int key, int value) {
            this.key = key ;
            this.value = value;
        }
    }
    private final int capacity;
    private final Map<Integer,Node> keyToNode = new HashMap<>();
    private final Map<Integer, Node> freToDummy = new HashMap<>();
    private int minfreq = 0;

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        Node node = getNode(key);
        return node != null ? node.value:-1;   
    }
    private Node getNode(int key) {
        if (!keyToNode.containsKey(key)) {
            return null;
        }
        Node node = keyToNode.get(key);
        remove(node);
       // fre Map update to rmv null 
        Node dummy = freToDummy.get(node.fre);
        if (dummy.next == dummy) {
            freToDummy.remove(node.fre);
            // redirect min value key postiion
            if (minfreq == node.fre) {
                minfreq++;
            }
        }
        addFront(++node.fre, node);
        return node;
    }
    
    public void put(int key, int value) {
      if (capacity == 0) {
        return;
      }
      Node node = keyToNode.get(key);
      if (node != null) {
         node.value = value;
         getNode(key);
         return ;
      } 
      if (keyToNode.size() == capacity) {
        Node dummy = freToDummy.get(minfreq);
        Node backNode = dummy.pre;
        keyToNode.remove(backNode.key);
        remove(backNode);
        if (dummy.pre == dummy) {
            freToDummy.remove(minfreq);
        }
        node = new Node(key, value);
        keyToNode.put(key,node);
        addFront(1,node);
        minfreq = 1;

      } 
    }
    private Node newList() {
        Node dummy = new Node(0,0);
        dummy.pre = dummy;
        dummy.next = dummy;
        return dummy;
    }

    private void addFront(int fre, Node node) {
        Node dummy = freToDummy.computeIfAbsent(fre,k -> newList());
        node.pre = dummy;
        node.next = dummy.next;
        dummy.next.pre = node;
        dummy.next = node;
    }
    private void remove(Node node) {
        node.next.pre = node.pre;
        node.pre.next = node.next;
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
// @lc code=end

