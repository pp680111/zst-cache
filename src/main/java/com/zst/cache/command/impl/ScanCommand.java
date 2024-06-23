package com.zst.cache.command.impl;

import com.zst.cache.command.Command;
import com.zst.cache.command.CommonReply;
import com.zst.cache.core.Cache;
import com.zst.cache.data.RESPArray;
import com.zst.cache.data.RESPBulkString;
import com.zst.cache.data.RESPData;
import com.zst.cache.data.RESPInteger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * SCAN命令
 *
 * MATCH和TYPE参数暂未实现
 */
public class ScanCommand extends Command {
    @Override
    protected String getName() {
        return "SCAN";
    }

    @Override
    public RESPData execute(Cache cache, RESPArray args) {
        List<RESPData> argList = args.getValue();

        if (args.getValue().size() < 2) {
            return CommonReply.WRONG_ARG_NUMBER;
        }

        RESPInteger cursor = (RESPInteger) argList.get(1);
        RESPInteger count = null;
        RESPBulkString matchPattern = null;
        RESPBulkString type = null;

        for (int i = 2; i < argList.size(); i = i + 2) {
            RESPBulkString argName = (RESPBulkString) argList.get(i);
            RESPData argValue = argList.get(i + 1);
            switch (argName.getValue()) {
                case "MATCH" :
                    matchPattern = (RESPBulkString) argValue;
                    break;
                case "COUNT":
                    count = (RESPInteger) argValue;
                    break;
                case "TYPE":
                    type = (RESPBulkString) argValue;
                    break;
                default:
            }
        }

        Set<String> keys = cache.getKeys();
        int index = 0;
        List<String> result = new ArrayList<>();

        for (String key : keys) {
            if (index < cursor.getValue()) {
                continue;
            }

            result.add(key);
            if (count != null && result.size() >= count.getValue()) {
                break;
            }
        }

        return buildReply(index, result);
    }

    private RESPArray buildReply(int index,  List<String> result) {
        RESPInteger nextCursor = new RESPInteger(index);
        List keys = result.stream().map(RESPBulkString::new).toList();
        RESPArray keysArray = new RESPArray(keys);
        return new RESPArray(Arrays.asList(nextCursor, keysArray));
    }
}
