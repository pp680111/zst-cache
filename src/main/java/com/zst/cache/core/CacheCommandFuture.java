package com.zst.cache.core;

import com.zst.cache.command.Command;
import com.zst.cache.data.RESPArray;
import com.zst.cache.data.RESPData;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.CompletableFuture;

/**
 * 缓存操作类，用于异步执行缓存操作
 */
@Getter
@Setter
public class CacheCommandFuture extends CompletableFuture<Void> {
    private Command command;
    private RESPArray args;
    private RESPData result;

    public CacheCommandFuture(Command command, RESPArray args) {
        this.command = command;
        this.args = args;
    }

}
