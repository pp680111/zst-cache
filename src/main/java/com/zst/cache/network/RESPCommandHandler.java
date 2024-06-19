package com.zst.cache.network;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.redis.RedisMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RESPCommandHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof RedisMessage)) {
            ctx.fireChannelRead(msg);
        }

        log.debug("RESPCommandHandler receive msg ", JSON.toJSONString(msg));
    }
}
