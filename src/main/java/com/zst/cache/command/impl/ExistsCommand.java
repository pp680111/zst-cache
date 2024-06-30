package com.zst.cache.command.impl;

import com.zst.cache.command.Command;
import com.zst.cache.command.CommonReply;
import com.zst.cache.core.Cache;
import com.zst.cache.data.RESPArray;
import com.zst.cache.data.RESPBulkString;
import com.zst.cache.data.RESPData;
import com.zst.cache.data.RESPInteger;

public class ExistsCommand extends Command {
    @Override
    public String getName() {
        return "EXISTS";
    }

    @Override
    public RESPData execute(Cache cache, RESPArray args) {
        long count = args.getValue().size() - 1;
        if (count == 0) {
            return CommonReply.WRONG_ARG_NUMBER;
        }

        int exists = 0;
        for (int i = 1; i <= count; i++) {
            RESPBulkString key = (RESPBulkString) args.getValue().get(i);
            if (cache.get(key.getValue()) != null) {
                exists++;
            }
        }

        return new RESPInteger(exists);
    }
}
