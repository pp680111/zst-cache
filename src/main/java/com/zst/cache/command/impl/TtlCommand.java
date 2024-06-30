package com.zst.cache.command.impl;

import com.zst.cache.command.Command;
import com.zst.cache.command.CommonReply;
import com.zst.cache.core.Cache;
import com.zst.cache.data.RESPArray;
import com.zst.cache.data.RESPBulkString;
import com.zst.cache.data.RESPData;
import com.zst.cache.data.RESPInteger;

public class TtlCommand extends Command {
    @Override
    protected String getName() {
        return "TTL";
    }

    @Override
    public RESPData execute(Cache cache, RESPArray args) {
        if (args.getValue().size() < 2) {
            return CommonReply.WRONG_ARG_NUMBER;
        }

        RESPBulkString key = (RESPBulkString) args.getValue().get(1);
        if (cache.get(key.getValue()) == null) {
            return CommonReply.NEG_2;
        }

        return new RESPInteger(cache.get(key.getValue()).getTtl());
    }
}
