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
            buffer.append((char) in.getByte(i));

            if (buffer.length() >= 2 && buffer.charAt(buffer.length() - 2) == '\r' && buffer.charAt(buffer.length() - 1) == '\n') {
                out.add(buffer.substring(0, buffer.length() - 2));
                buffer.delete(0, buffer.length());
            }
        }

        // TODO 要处理一下没有数据可返回的情况莫不然会抛出异常RESPDecoder.decode() did not read anything but decoded a message

//        int readableBytes = in.readableBytes();
//        byte[] bytes = new byte[readableBytes];
//        in.readBytes(bytes, 0, readableBytes);
//
//        out.add(new String(bytes, StandardCharsets.UTF_8));
        // 老师的demo是直接吧整个buffer里面的数据读出来转成字符串，但是这样不会出现半包或者粘包问题吗？
    }
}
