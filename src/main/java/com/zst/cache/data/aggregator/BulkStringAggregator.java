package com.zst.cache.data.aggregator;

/**
 * 大字符串的聚合器
 */
public class BulkStringAggregator implements RESPAggregator {
    private int expectLength = -1;
    private boolean isComplete = false;
    private String result;

    public void append(String line) {
        if (line.startsWith("$")) {
            expectLength = Integer.parseInt(line.substring(1));
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
    public Object getData() {
        if (!isComplete) {
            throw new IllegalStateException("BulkString is not complete");
        }

        return result;
    }

}
