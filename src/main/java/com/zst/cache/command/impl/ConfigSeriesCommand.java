package com.zst.cache.command.impl;

import com.zst.cache.command.Command;
import com.zst.cache.command.CommonReply;
import com.zst.cache.core.Cache;
import com.zst.cache.data.RESPArray;
import com.zst.cache.data.RESPBulkString;
import com.zst.cache.data.RESPData;
import com.zst.cache.data.RESPInteger;

import java.util.ArrayList;
import java.util.List;

/**
 * config系列命令
 */
public class ConfigSeriesCommand extends Command {
    @Override
    protected String getName() {
        return "CONFIG";
    }

    @Override
    public RESPData execute(Cache cache, RESPArray args) {
        List<RESPData> argList = args.getValue();
        if (argList.size() < 2) {
            return CommonReply.WRONG_ARG_NUMBER;
        }

        String subCommand = ((RESPBulkString) argList.get(1)).getValue();
        subCommand =subCommand.toLowerCase();
        switch(subCommand) {
            case "get":
                return executeGetCommand(cache, args);
            default:
                return CommonReply.UNKNOWN_COMMAND;
        }
    }

    /**
     * config get命令
     * @param cache
     * @param args
     * @return
     */
    public RESPData executeGetCommand(Cache cache, RESPArray args) {
        // CONFIG GET命令参数数量至少为3
        if (args.getValue().size() < 3) {
            return CommonReply.WRONG_ARG_NUMBER;
        }

        List<RESPData> resultValueList = new ArrayList<>();

        String arg = ((RESPBulkString) args.getValue().get(2)).getValue();
        switch (arg) {
            case "databases":
                resultValueList.add(new RESPBulkString("databases"));
                resultValueList.add(new RESPInteger(1));
                break;
        }

        return new RESPArray(resultValueList);
    }
}
