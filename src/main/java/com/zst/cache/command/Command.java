package com.zst.cache.command;

import com.zst.cache.core.Cache;
import com.zst.cache.data.RESPData;

/**
 * 命令
 */
public abstract class Command {
    /**
     * 命令执行的入口方法
     * @param cache
     * @param args
     * @return
     */
    public abstract RESPData execute(Cache cache, RESPData args);
}
