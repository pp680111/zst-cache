package com.zst.cache.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class RESPDecoder extends ByteToMessageDecoder {
    ByteBuf buffer;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() == 0) {
            return;
        }

        if (buffer == null) {
            buffer = channelHandlerContext.alloc().heapBuffer();
        }

        int readableBytes = in.readableBytes();
        for (int i = 0; i < readableBytes; i++) {
            byte b = in.readByte();

        }

        int readableBytes = in.readableBytes();
        byte[] bytes = new byte[readableBytes];
        in.readBytes(bytes, 0, readableBytes);

        out.add(new String(bytes, StandardCharsets.UTF_8));
        // 老师的demo是直接吧整个buffer里面的数据读出来转成字符串，但是这样不会出现半包或者粘包问题吗？
    }
}
