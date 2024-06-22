package com.zst.cache.data.aggregator;

import com.zst.cache.data.RESPBulkString;
import com.zst.cache.data.RESPData;

import java.nio.charset.StandardCharsets;

/**
 * 大字符串的聚合器
 */
public class BulkStringAggregator implements RESPAggregator {
    private int expectLength = -1;
    private boolean isComplete = false;
    private String result;

    public void append(String line) {
        if (isComplete) {
            throw new IllegalStateException("data aggregate is complete");
        }

        if (line.startsWith("$")) {
            expectLength = Integer.parseInt(line.substring(1));

            // 如果长度为1的话，说明是一个null字符串，直接complete
            if (expectLength == -1) {
                isComplete = true;
            }
            return;
        }

        if (expectLength > 0 && line.length() != expectLength) {
            throw new RuntimeException("BulkString length not match");
        }

        result = line;
        isComplete = true;
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public RESPData getData() {
        if (!isComplete) {
            throw new IllegalStateException("BulkString is not complete");
        }

        return new RESPBulkString(result);
    }

}
