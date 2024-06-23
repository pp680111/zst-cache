package com.zst.cache.command.impl;

import com.zst.cache.command.Command;
import com.zst.cache.command.CommonReply;
import com.zst.cache.core.Cache;
import com.zst.cache.data.RESPArray;
import com.zst.cache.data.RESPData;

import java.util.List;

public class ConfigCommand extends Command {
    @Override
    protected String getName() {
        return "CLIENT";
    }

    @Override
    public RESPData execute(Cache cache, RESPArray args) {
        List<RESPData> argList = args.getValue();
        if (argList.size() < 2) {
            return CommonReply.WRONG_ARG_NUMBER;
        }
    }

    public RESPData executeDatabasesCommand(Cache cache, RESPArray args) {

    }
}
