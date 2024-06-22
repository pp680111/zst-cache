package com.zst.cache.network;

import com.alibaba.fastjson2.JSON;
import com.zst.cache.data.RESPArray;
import com.zst.cache.data.RESPBulkString;
import com.zst.cache.data.RESPSimpleString;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.redis.RedisMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class RESPCommandHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof RedisMessage)) {
            ctx.fireChannelRead(msg);
        }

        log.debug("RESPCommandHandler receive msg ", JSON.toJSONString(msg));

        if (msg instanceof RESPArray) {
            RESPArray commandArray = (RESPArray) msg;
            RESPBulkString command = (RESPBulkString) commandArray.getValue().get(0);
            switch (command.getValue()) {
                case "ping":
                    ctx.writeAndFlush(new RESPSimpleString("PONG"));
                    break;
                case "client":
                    ctx.writeAndFlush(new RESPSimpleString("OK"));
                    break;
                case "config":
                    RESPArray configArray = new RESPArray();
                    configArray.setValue(Arrays.asList(new RESPBulkString("databases"), new RESPBulkString("16")));
                    ctx.writeAndFlush(configArray);
                    break;
                case "scan":
                    RESPArray scanArray = new RESPArray();
                    scanArray.setValue(Arrays.asList(new RESPBulkString("0"), new RESPBulkString("zst"), new RESPBulkString("tsz")));
                    ctx.writeAndFlush(scanArray);
                    break;
                default:
                    ctx.writeAndFlush(new RESPSimpleString("ERR unknown command '"+ command.getValue() + "'"));
            }
        }
    }
}
