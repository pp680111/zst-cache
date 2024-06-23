package com.zst.cache.core;

public class CacheFactory {
    private static Cache cache;

    public static Cache getCache() {
        if (cache == null) {
            synchronized (CacheFactory.class) {
                if (cache == null) {
                    cache = new Cache();
                }
            }
        }

        return cache;
    }

}
