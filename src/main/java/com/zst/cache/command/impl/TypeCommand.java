package com.zst.cache.command.impl;

import com.zst.cache.command.Command;
import com.zst.cache.command.CommonReply;
import com.zst.cache.core.Cache;
import com.zst.cache.core.CacheEntity;
import com.zst.cache.core.EntityType;
import com.zst.cache.data.RESPArray;
import com.zst.cache.data.RESPBulkString;
import com.zst.cache.data.RESPData;
import com.zst.cache.data.RESPSimpleString;

public class TypeCommand extends Command {
    @Override
    protected String getName() {
        return "TYPE";
    }

    @Override
    public RESPData execute(Cache cache, RESPArray args) {
        String arg = ((RESPBulkString) args.getValue().get(1)).getValue();
        CacheEntity value = cache.get(arg);
        if (value == null) {
            return CommonReply.NONE;
        }

        String type = "";
        switch (value.getType()) {
            case EntityType.STRING:
                type = "string";
                break;
            case EntityType.LIST:
                type = "list";
                break;
            case EntityType.SET:
                type = "set";
                break;
            case EntityType.HASH:
                type = "hash";
                break;
        }

        if (type.isEmpty()) {
            return CommonReply.UNKNOWN_ERROR;
        }
        return new RESPSimpleString(type);
    }

}

