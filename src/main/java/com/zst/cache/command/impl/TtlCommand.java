package com.zst.cache.command.impl;

import com.zst.cache.command.Command;
import com.zst.cache.core.Cache;
import com.zst.cache.data.RESPArray;
import com.zst.cache.data.RESPData;
import com.zst.cache.data.RESPInteger;

public class TtlCommand extends Command {
    @Override
    protected String getName() {
        return "TTL";
    }

    @Override
    public RESPData execute(Cache cache, RESPArray args) {
        return new RESPInteger(-1);
    }
}
