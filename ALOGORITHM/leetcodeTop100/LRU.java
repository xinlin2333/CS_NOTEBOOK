class Node{
    private int key;
    private int val;
    public Node(int key,int val){
        this.key = key;
        this.val = val;
    }

}
class DoubleList {
    private Node head;
    private Node tail;
    private int size;
    public void addFirst(Node node){
        if(head == null){
            head = tail = node;
        }else{
            Node n = head;
            n.prev = node;
            node.next = n;
            head = node;
        }
        size++;
    }
    public void remove(Node node){
        if(head == null && tail ==node){
            head = null;
            tail = null;
        }else if(tail ==node){
            node.prev.next = null;
            tail = node.prev;
        }else if(head ==node){
            node.next.prev = null;
            head  = node.next;
        }else{
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
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

class Solution{
    private HashMap<Integer,Node> map;
    private DoubleList cache;
    private int cap;

    public LRUCache(int capacity){
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }
    public int get(int key){
        if(!map.containsKey(key)){
            return -1;
        }
        int val = map.get(key).val;
        put(key,val);
        return val;
    }
    public void put(int key,int value){
        Node x = new Node(key,value);
        if(map.containsKey(key)){
            cache.remove(map.get(key));
            cache.addFirst(x);
            map.put(key,x);
        }else{
            if(cap == cache.size()){
                Node last = cache.removeLast();
                map.remove(last.key);
            }
            cache.addFirst(x);
            map.put(key,x);
        }
    }
}