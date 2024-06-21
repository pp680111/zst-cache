package com.zst.cache.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class RESPDecoder extends ByteToMessageDecoder {
    private static final String LFCR = "\r\n";

    StringBuilder buffer = new StringBuilder();

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() == 0) {
            return;
        }

        int readableBytes = in.readableBytes();
        for (int i = 0; i < readableBytes; i++) {
            buffer.append((char) in.readByte());

            if (buffer.length() >= 2 && buffer.charAt(buffer.length() - 2) == '\r' && buffer.charAt(buffer.length() - 1) == '\n') {
                out.add(buffer.substring(0, buffer.length() - 2));
                buffer.delete(0, buffer.length());
            }
        }
    }
}
