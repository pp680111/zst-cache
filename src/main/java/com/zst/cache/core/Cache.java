package com.zst.cache.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 缓存数据核心
 */
public class Cache {
    private Map<String, CacheEntity> coreMap = new HashMap<>(512);

    public void put(String key, CacheEntity value) {
        coreMap.put(key, value);
    }

    public CacheEntity get(String key) {
        return coreMap.get(key);
    }

    public Set<String> getKeys() {
        return coreMap.keySet();
    }
}
