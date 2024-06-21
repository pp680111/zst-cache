package com.zst.cache.data.aggregator;

import com.zst.cache.data.RESPData;
import com.zst.cache.data.RESPInteger;

public class IntegerAggregator implements RESPAggregator {
    private long value;
    private boolean isComplete;
    @Override
    public void append(String line) {
        if (isComplete) {
            throw new IllegalStateException("Aggregator is already complete");
        }
        value = Long.parseLong(line.substring(1));
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public RESPData getData() {
        if (!isComplete) {
            throw new IllegalStateException("Aggregator is not complete");
        }

        return new RESPInteger(value);
    }
}
