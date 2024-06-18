package com.zst.cache.network;

import com.zst.cache.data.aggregator.BulkStringAggregator;
import com.zst.cache.data.aggregator.RESPAggregator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 用来将分散的数据聚合成一份完整的RESP协议的数据的数据聚合器
 */
public class RESPDataAggregator extends ChannelInboundHandlerAdapter {
    private RESPAggregator aggregator;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String value = (String) msg;
        if (aggregator == null) {
            aggregator = createAggregator(value);
        }

        aggregator.append(value);

        if (aggregator.isComplete()) {
            ctx.fireChannelRead(aggregator.getData());
            aggregator = null;
        }
    }

    private RESPAggregator createAggregator(String line) {
        if (line.startsWith("+")) {
//            return new RESPAggregator.SimpleStringAggregator();
        } else if (line.startsWith("-")) {
//            return new RESPAggregator.ErrorAggregator();
        } else if (line.startsWith(":")) {
//            return new RESPAggregator.IntegerAggregator();
        } else if (line.startsWith("$")) {
            return new BulkStringAggregator();
        } else if (line.startsWith("*")) {
//            return new RESPAggregator.ArrayAggregator();
        }
        throw new RuntimeException("Invalid RESP data");
    }
}
