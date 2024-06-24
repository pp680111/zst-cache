package com.zst.cache.network;

import com.alibaba.fastjson2.JSON;
import com.zst.cache.command.Command;
import com.zst.cache.command.CommandManager;
import com.zst.cache.command.CommonReply;
import com.zst.cache.core.Cache;
import com.zst.cache.data.RESPArray;
import com.zst.cache.data.RESPBulkString;
import com.zst.cache.data.RESPData;
import com.zst.cache.data.RESPSimpleString;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.redis.RedisMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@AllArgsConstructor
@Slf4j
public class RESPCommandHandler extends ChannelInboundHandlerAdapter {
    private Cache cache;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof RESPData)) {
            ctx.fireChannelRead(msg);
        }

        RESPArray commandArray = (RESPArray) msg;
        Command command = CommandManager.getCommand(commandArray);
        RESPData result = executeCommand(command, commandArray);

        ctx.writeAndFlush(result);

    }

    private RESPData executeCommand(Command command, RESPArray commandArray) {
        if (command == null) {
            return CommonReply.UNKNOWN_COMMAND;
        }

        try {
            RESPData result = command.execute(cache, commandArray);
            if (result != null) {
                return result;
            } else {
                throw new RuntimeException("Command execute failed");
            }
        } catch (Exception e) {
            log.error("execute command failed", e);
            return CommonReply.EXECUTE_FAILED;
        }
    }
}
