package com.camunda.exporter.config;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class SimpleCache<K, V> {
    private final Map<K, V> cache;

    public SimpleCache() {
        this.cache = new HashMap<>();
    }

    // Method to put a value in the cache
    public void put(K key, V value) {
        cache.put(key, value);
    }

    // Method to get a value from the cache
    public V get(K key) {
        return cache.get(key);
    }

    // Method to remove a value from the cache
    public void remove(K key) {
        cache.remove(key);
    }

    // Method to clear the cache
    public void clear() {
        cache.clear();
    }

    // Method to check if the cache contains a key
    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }

    // Method to get the size of the cache
    public int size() {
        return cache.size();
    }
}