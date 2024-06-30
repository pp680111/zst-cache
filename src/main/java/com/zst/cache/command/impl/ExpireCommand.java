package com.zst.cache.command.impl;

import com.zst.cache.command.Command;
import com.zst.cache.command.CommonReply;
import com.zst.cache.core.Cache;
import com.zst.cache.core.CacheEntity;
import com.zst.cache.data.RESPArray;
import com.zst.cache.data.RESPBulkString;
import com.zst.cache.data.RESPData;
import com.zst.cache.data.RESPInteger;

public class ExpireCommand extends Command {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public RESPData execute(Cache cache, RESPArray args) {
        if (args.getValue().size() <= 3) {
            return CommonReply.WRONG_ARG_NUMBER;
        }

        RESPBulkString key = (RESPBulkString) args.getValue().get(1);
        RESPInteger second = (RESPInteger) args.getValue().get(2);
        RESPBulkString option = null;
        if (args.getValue().size()== 4) {
            option = (RESPBulkString) args.getValue().get(3);
        }

        CacheEntity entity = cache.get(key.getValue());
        if (entity == null) {
            return CommonReply.ZERO;
        }


    }
}
