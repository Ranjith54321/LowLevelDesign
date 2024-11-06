package org.example;

/**
 * Created by Ranjith S on 06/11/24.
 **/
public class LRUCacheDemo {
    public static void main(String[] args) {
        System.out.println("LRU Cache Demo!!!!");

        LRUCache<Integer, String> lruCache = new LRUCache<>(3); // set Initial Capacity.
        lruCache.put(1, "Value 1");
        lruCache.put(2, "Value 2");
        lruCache.put(3, "Value 3");

        System.out.println(lruCache.get(1)); // Output: Value 1
        System.out.println(lruCache.get(2)); // Output: Value 2

        lruCache.put(4, "Value 4");

        System.out.println(lruCache.get(3)); // Output: null
        System.out.println(lruCache.get(4)); // Output: Value 4

        lruCache.put(2, "Updated Value 2");

        System.out.println(lruCache.get(1)); // Output: Value 1
        System.out.println(lruCache.get(2)); // Output: Updated Value 2


        // check the Notion notes.

    }
}