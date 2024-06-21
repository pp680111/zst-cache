package com.zst.cache.data.aggregator;

import com.zst.cache.data.RESPData;
import com.zst.cache.data.RESPErrors;

/**
 * 错误数据聚合器
 */
public class ErrorAggregator implements RESPAggregator {
    private String type;
    private String error;
    private boolean complete = false;

    @Override
    public void append(String line) {
        if (complete) {
            throw new IllegalStateException("Aggregator is already complete");
        }

        int index = line.indexOf(' ');
        type = line.substring(1, index);
        error = line.substring(index + 1);
        complete = true;
    }

    @Override
    public boolean isComplete() {
        return complete;
    }

    @Override
    public RESPData getData() {
        if (!complete) {
            throw new IllegalStateException("Aggregator is not complete");
        }

        return new RESPErrors(type, error);
    }
}
