package com.zst.cache.command.impl;

import com.zst.cache.command.Command;
import com.zst.cache.core.Cache;
import com.zst.cache.data.RESPData;
import com.zst.cache.data.RESPSimpleString;

/**
 * PING命令
 */
public class PingCommand extends Command {
    @Override
    public RESPData execute(Cache cache, RESPData args) {
        return new RESPSimpleString("PONG");
    }
}
