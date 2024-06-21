package com.zst.cache.network;

import com.zst.cache.data.RESPData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.List;

public class RESPEncoder extends MessageToByteEncoder<RESPData> {
    @Override
    protected void encode(ChannelHandlerContext ctx, RESPData msg, ByteBuf out) throws Exception {
        List<String> lines = msg.toLines();
        for (String line : lines) {
            out.writeBytes(line.getBytes());
            out.writeBytes("\r\n".getBytes());
        }
    }
}
