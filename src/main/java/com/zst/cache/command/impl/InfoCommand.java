package com.zst.cache.command.impl;

import com.zst.cache.command.Command;
import com.zst.cache.core.Cache;
import com.zst.cache.data.RESPArray;
import com.zst.cache.data.RESPBulkString;
import com.zst.cache.data.RESPData;

public class InfoCommand extends Command {
    private static RESPBulkString mockInfo = new RESPBulkString("this is zst custom RESP protocol implement");
    @Override
    protected String getName() {
        return "INFO";
    }

    @Override
    public RESPData execute(Cache cache, RESPArray args) {
        return mockInfo;
    }
}
