package com.zst.cache.data.aggregator;

public class RESPAggregatorFactory {
    public static RESPAggregator create(String line) {
        if (line.startsWith("+")) {
            return new SimpleStringAggregator();
        }
        if (line.startsWith("-")) {
            return new ErrorAggregator();
        }
        if (line.startsWith(":")) {
            return new IntegerAggregator();
        }
        if (line.startsWith("$")) {
            return new BulkStringAggregator();
        }
        if (line.startsWith("*")) {
            return new ArrayAggregator();
        }

        throw new RuntimeException("Unknown data type");
    }
}
