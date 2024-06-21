package com.zst.cache.data.aggregator;

import com.zst.cache.data.RESPArray;
import com.zst.cache.data.RESPData;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据类型的数据聚合器
 */
public class ArrayAggregator implements RESPAggregator {
    private List<RESPData> value;
    private RESPAggregator aggregator;
    private boolean isComplete = false;
    private int size;

    @Override
    public void append(String line) {
        if (isComplete) {
            throw new IllegalStateException("data aggregate is complete");
        }

        if (aggregator == null && line.startsWith("*")) {
            size = Integer.parseInt(line.substring(1));

            // -1的size表示数据内容是null
            if (size == -1) {
                isComplete = true;
                return;
            }

            // 0的size表示数据内容是空数组
            if (size == 0) {
                value = new ArrayList<>();
                isComplete = true;
                return;
            }

            value = new ArrayList<>(size);
            return;
        }

        if (aggregator == null) {
            aggregator = RESPAggregatorFactory.create(line);
        }

        aggregator.append(line);
        if (aggregator.isComplete()) {
            value.add(aggregator.getData());
            aggregator = null;
        }

        if (value.size() == size) {
            isComplete = true;
        }
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public RESPData getData() {
        if (!isComplete) {
            throw new IllegalStateException("data aggregate not complete");
        }
        RESPArray array = new RESPArray();
        array.setValue(value);
        return array;
    }
}
