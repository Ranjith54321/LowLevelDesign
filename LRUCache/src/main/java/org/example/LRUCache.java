package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ranjith S on 06/11/24.
 **/
public class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, Node<K, V>> cache;
    private final Node<K, V> head;
    private final Node<K, V> tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);
        head = new Node<>(null, null);
        tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    public synchronized K get(K key) {
        Node<K, V> node = cache.get(key);
        if (node == null) {
            return null;
        }
        moveHead(node);
        return node.key;
    }

    public synchronized void put(K key, V value) {
        Node<K, V> node = cache.get(key);
        if (node != null) {
            node.value = value;
            moveHead(node);
        } else {
            node = new Node<>(key, value);
            cache.put(key, node);
            addToHead(node);
            if(cache.size() > capacity) {
                Node nodeToBeRemoved = removeTail();
                cache.remove(nodeToBeRemoved.key); // we must remove from map..
            }
        }
    }

    private Node removeTail() { // Tail is used to extract last Node in fast.
        Node<K, V> node = tail.prev;
        removeNode(node);
        return node;
    }

    private void moveHead(Node<K, V> node) {
        removeNode(node);
        addToHead(node);
    }

    private void addToHead(Node<K, V> node) { // tail is always a tail and head is always head.
        node.prev = head; // Head is used to extract first Node in fast.
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
