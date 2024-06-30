package com.zst.cache.command.impl;

import com.zst.cache.command.Command;
import com.zst.cache.command.CommonReply;
import com.zst.cache.core.Cache;
import com.zst.cache.core.StringEntity;
import com.zst.cache.data.RESPArray;
import com.zst.cache.data.RESPBulkString;
import com.zst.cache.data.RESPData;

/**
 * GET命令
 *
 */
public class GetCommand extends Command {
    @Override
    protected String getName() {
        return "GET";
    }

    @Override
    public RESPData execute(Cache cache, RESPArray args) {
        RESPBulkString key = (RESPBulkString) args.getValue().get(1);
        if (cache.get(key.getValue()) == null) {
            return CommonReply.NULL_BULK_STRING;
        }

        Object result = cache.get(key.getValue());
        if (!(result instanceof StringEntity)) {
            return CommonReply.WRONG_DATA_TYPE;
        }

        StringEntity stringEntity = (StringEntity) result;
        return new RESPBulkString(stringEntity.getValue());
    }
}
