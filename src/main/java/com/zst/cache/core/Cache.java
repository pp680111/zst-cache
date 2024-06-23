package com.zst.cache.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 缓存数据核心
 */
public class Cache {
    private Map<String, Object> coreMap = new HashMap<>(512);

    public void put(String key, Object value) {
        coreMap.put(key, value);
    }

    public Object get(String key) {
        return coreMap.get(key);
    }

    public Set<String> getKeys() {
        return coreMap.keySet();
    }
}
