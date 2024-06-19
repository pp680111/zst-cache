package com.zst.cache.network;

import com.zst.cache.data.aggregator.RESPAggregator;
import com.zst.cache.data.aggregator.RESPAggregatorFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * 用来将分散的数据聚合成一份完整的RESP协议的数据的数据聚合器
 */
@Slf4j
public class RESPDataAggregator extends ChannelInboundHandlerAdapter {
    private RESPAggregator aggregator;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String value = (String) msg;
        log.debug("RESPAggregator received {}", value);

        if (aggregator == null) {
            aggregator = RESPAggregatorFactory.create(value);
        }

        aggregator.append(value);

        if (aggregator.isComplete()) {
            ctx.fireChannelRead(aggregator.getData());
            aggregator = null;
        }
    }

}
