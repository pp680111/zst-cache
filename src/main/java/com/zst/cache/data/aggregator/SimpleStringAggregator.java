package com.zst.cache.data.aggregator;

import com.zst.cache.data.RESPSimpleString;

/**
 * 简单字符串的聚合器
 */
public class SimpleStringAggregator implements RESPAggregator {
    private String data;

    @Override
    public void append(String line) {
        if (data != null) {
            throw new IllegalStateException("data aggregate is complete");
        }

        data = line.substring(1);
    }

    @Override
    public boolean isComplete() {
        return data != null;
    }

    @Override
    public Object getData() {
        if (data == null) {
            throw new IllegalStateException("BulkString is not complete");
        }

        return new RESPSimpleString(data);
    }
}
