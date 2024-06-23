package com.zst.cache.command.impl;

import com.zst.cache.command.Command;
import com.zst.cache.command.CommonReply;
import com.zst.cache.core.Cache;
import com.zst.cache.data.RESPArray;
import com.zst.cache.data.RESPBulkString;
import com.zst.cache.data.RESPData;

import java.util.List;

/**
 * SET命令
 *
 * 仅支持最基本的SET KEY VALUE的形式
 */
public class SetCommand extends Command {
    @Override
    protected String getName() {
        return "SET";
    }

    @Override
    public RESPData execute(Cache cache, RESPArray args) {
        List<RESPData> argList = args.getValue();
        if (argList.size() < 3) {
            return CommonReply.WRONG_ARG_NUMBER;
        }

        RESPBulkString key = (RESPBulkString) args.getValue().get(1);
        RESPBulkString value = (RESPBulkString) args.getValue().get(2);

        if (key.getValue() == null ||  value.getValue() == null) {
            return CommonReply.WRONG_ARG_TYPE;
        }

        cache.put(key.getValue(), value.getValue());

        return CommonReply.OK;
    }
}
