package ALOGORITHM.leetcodeTop100;

/**
 * @author canoeYang
 * @Date 2020-08-04 9:13
 */

import java.util.HashMap;
import java.util.List;

/**
 * 思路：首先LRU做到查找的复杂度为O（1）我们可以想到为hashmap，因为是key，value
 * 其次对于LRU删除最近最少使用的特性，可以使用双向链表，将最少使用的放在链表i的末尾，每次一旦使用了某个value，将其使用次数即位置更新到
 * 最前面，这样就能保证了特性
 * 而map和双向链表的之间的联系，就在于，map的value是双向链表的一个节点，指向node，这样就可以做的查询O(1)
 */
public class LRUCache {
    public static void main(String[] args){

    }

    /**
     * LRU由linkedHashMap实现，但是其底层是hashmap+doubleList
     * Hashmap作为索引，保证了查找为O（1）;hashmap的value对应的是doubleList的一个节点Node
     */
    HashMap<Integer,Node>  map;
    DoubleList cache;
    private int capacity;
    public LRUCache(int capacity) {
        this.capacity =capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    /**
     * 查找key分为两种：1、没有找到，返回-1；
     * 2、找到，那么同时需要将这个节点放到最前面
     * @param key
     * @return
     */
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }else{
            int res = map.get(key).val;
            put(key,res);
            return res;
        }
    }

    /**
     * 存放key，value；
     * 分三种情况
     * 1、存在key，那么需要将在cache中的位置挪到最前面
     * 2、不存在key，但是已经满了，需要删除最近最久未使用的，删除cache，删除key
     * 3、不存在key，cache插入到最前面，map中插入索引
     * @param key
     * @param value
     */

    public void put(int key, int value) {
        Node node = new Node(key,value);
        if(map.containsKey(key)){
            cache.remove(map.get(key));
            cache.addFirst(node);
            map.put(key,node);
        }else{
           if(capacity == cache.size()){
               Node tmp = cache.removeLast();
               map.remove(tmp.key);
           }
            cache.addFirst(node);
            map.put(key,node);
        }
    }
}
class Node{
    int key,val;
    Node pre,next;
    public Node(int key,int val){
        this.key = key;
        this.val = val;
    }
}
class DoubleList{
    private Node head;
    private Node tail;
    int size;

    /**
     * 新增节点：在头结点插入，这里得分空链表和非空链表
     * 空链表：head = tail = node
     * 非空链表：node.next = head;头结点应该为插入的节点
     * @param node
     */
    public void addFirst(Node node){
        if(head == null){
            head = tail = node;
        }else{
            Node n = head;
            n.pre = node;
            node.next = n;
            head = node;
        }
        size++;

    }

    /**
     * 删除节点时，得分空链表，尾部删除，头部删除，以及中间删除
     * 空链表：依旧为空
     * 尾部删除：node.pre.next = null; node.pre = tail;
     * 首部删除：node.next.pre = null; node.next = head;
     * 中间删除：node.next.pre = node.pre; node.pre.next =node.next;
     * @param node
     */
    public void remove(Node node){
        if(head == node && tail ==node){
            head = null;
            tail = null;
        }else if(tail == node){
            node.pre.next = null;
            tail = node.pre;
        }else if(head == node){
            node.next.pre = null;
            head = node.next;
        }else{
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        size--;
    }
    public Node removeLast(){
        Node node = tail;
        remove(tail);
        return node;
    }
    public int size(){
        return size;
    }
}

