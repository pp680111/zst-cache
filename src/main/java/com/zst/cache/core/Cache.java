package com.zst.cache.core;

import com.zst.cache.command.Command;
import com.zst.cache.data.RESPArray;
import com.zst.cache.data.RESPData;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 缓存数据核心
 */
@Slf4j
public class Cache {
    private final Thread coreThread;
    private Map<String, CacheEntity> coreMap;
    private final BlockingQueue<CacheCommandFuture> operationQueue;
    private long lastTimeMillis;

    public Cache() {
        this.coreThread = new Thread(new CacheCoreRunner());
        this.coreThread.setName("cache-core-thread");

        this.coreMap = new HashMap<>(512);
        this.operationQueue = new LinkedBlockingQueue<>();

        coreThread.start();
    }

    public CacheCommandFuture executeCommand(Command command, RESPArray args) {
        CacheCommandFuture future = new CacheCommandFuture(command, args);
        this.operationQueue.add(future);
        return future;
    }

    public void put(String key, CacheEntity value) {
        coreMap.put(key, value);
    }

    public CacheEntity get(String key) {
        return coreMap.get(key);
    }

    public Set<String> getKeys() {
        return coreMap.keySet();
    }

    /**
     * 内部core线程调用，负责执行添加到队列中的命令
     */
    private void executeCommand() {
        CacheCommandFuture commandFuture = null;
        try {
            commandFuture = operationQueue.poll(1, TimeUnit.MILLISECONDS);
            if (commandFuture == null) {
                return;
            }

            RESPData result = commandFuture.getCommand().execute(this, commandFuture.getArgs());
            commandFuture.setResult(result);
            commandFuture.complete(null);
        } catch (Exception e) {
            log.error("execute command error", e);
            if (commandFuture != null) {
                commandFuture.completeExceptionally(e);
            }
        }
    }

    /**
     * 内部核心线程调用-
     */
    private void updateTtl() {
        long currentTimeMillis = System.currentTimeMillis();
        if ((currentTimeMillis - lastTimeMillis) > 1000) {
            this.lastTimeMillis =  currentTimeMillis;
            this.coreMap.forEach((key, value) -> {
                if (value.getTtl() > 0) {
                    value.setTtl(value.getTtl() - 1);
                }
            });
        }
    }

    /**
     *  缓存核心线程
     */
    private class CacheCoreRunner implements Runnable {

        @Override
        public void run() {
            while (true) {
                // 从command队列中拿出可以执行的命令
                executeCommand();
                // 更新ttl
                updateTtl();
                // 执行数据删除
            }
        }


    }

}
