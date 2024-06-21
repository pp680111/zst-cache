package com.zst.cache.data.aggregator;

import com.zst.cache.data.RESPData;

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
    RESPData getData();
}
