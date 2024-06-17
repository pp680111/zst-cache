package com.zst.cache;

import com.zst.cache.network.Server;
import com.zst.cache.network.ServerConfig;

import java.util.concurrent.locks.LockSupport;

public class ServerTest {
    public static void main(String[] args) {
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setPort(6370);

        Server server = new Server(serverConfig);
        server.start();

        LockSupport.park();
    }
}
