package com.camunda.exporter.service;

import com.camunda.exporter.config.SimpleCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    private final SimpleCache<String, String> simpleCache;

    @Autowired
    public CacheService(SimpleCache<String, String> simpleCache) {
        this.simpleCache = simpleCache;
    }

    public void cacheValue(String key, String value) {
        simpleCache.put(key, value);
    }

    public String getCachedValue(String key) {
        return simpleCache.get(key);
    }

    public void removeCachedValue(String key) {
        simpleCache.remove(key);
    }

    public void clearCache() {
        simpleCache.clear();
    }
}