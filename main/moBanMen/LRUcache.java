package moBanMen;

import java.util.HashMap;
import java.util.Map;

public class LRUcache {
    class Node{
        private int key;
        private int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            prev = null;
            next = null;
        }

    }

    private Map<Integer, Node> cache;
    private int capacity;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }


    public int get(int key) {
        if(cache.containsKey(key)){
            Node cur = cache.get(key);
            deleteNode(cur);
            addTohead(cur);
            return cur.value;
        }else{
            return -1;
        }
    }

    public void put(int key, int value) {
        if(cache.containsKey(key)) {
            deleteNode(cache.get(key));
        }
        Node cur = new Node(key,value);
        cache.put(key, cur);
        addTohead(cur);
        if(cache.size() > capacity) {
            cache.remove(tail.prev.key);
            deleteNode(tail.prev);
        }
    }

    public void addTohead(Node node) {
        head.next.prev = node;
        node.next = head.next;
        node.prev = head;
        head.next = node;
    }


    public void deleteNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }


}
