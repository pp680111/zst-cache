package com.zst.cache.core;

public class StringEntity extends CacheEntity {
    private String value;

    public StringEntity() {
        super(EntityType.STRING);
    }

    public StringEntity(String value) {
        super(EntityType.STRING);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
