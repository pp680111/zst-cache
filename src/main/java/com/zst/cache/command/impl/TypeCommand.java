package com.zst.cache.command.impl;

import com.zst.cache.command.Command;
import com.zst.cache.command.CommonReply;
import com.zst.cache.core.Cache;
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
        Object value = cache.get(arg);
        if (value == null) {
            return CommonReply.NONE;
        }

        return new RESPSimpleString(checkValueType(cache, value));
    }

    private String checkValueType(Cache cache, Object value) {
        // TODO 临时应付的实现，后续需调整
        return "string";
    }
}

