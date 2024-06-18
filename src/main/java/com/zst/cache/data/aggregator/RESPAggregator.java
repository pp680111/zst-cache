package com.zst.cache.data.aggregator;

public interface RESPAggregator {
    /**
     * 追加数据
     * @param line
     */
    void append(String line);

    /**
     * 是否完成
     * @return
     */
    boolean isComplete();

    /**
     * 获取数据
     * @return
     */
    Object getData();
}
