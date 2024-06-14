package com.zst.cache.network;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerConfig {
    private int port;
    private int ioWorkerCount = 2;
    private int workerCount = Runtime.getRuntime().availableProcessors();
}
