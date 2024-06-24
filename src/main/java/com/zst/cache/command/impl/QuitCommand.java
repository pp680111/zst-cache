package com.zst.cache.command.impl;

import com.zst.cache.command.Command;
import com.zst.cache.command.CommonReply;
import com.zst.cache.core.Cache;
import com.zst.cache.data.RESPArray;
import com.zst.cache.data.RESPData;

public class QuitCommand extends Command {
    @Override
    protected String getName() {
        return "QUIT";
    }

    @Override
    public RESPData execute(Cache cache, RESPArray args) {
        return CommonReply.OK;
    }
}
