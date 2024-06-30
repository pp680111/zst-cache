package com.zst.cache.core;

public abstract class CacheEntity {
    public CacheEntity(int type) {
        this.type = type;
    }

    /**
     * TTL
     */
    private int ttl = -1;
    /**
     * 缓存值类型
     */
    private int type;

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public int getType() {
        return type;
    }
}
