package com.zst.cache.network;

import com.zst.cache.core.CacheFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.redis.RedisDecoder;
import io.netty.handler.codec.redis.RedisEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server {
    private ServerConfig config;
    EventLoopGroup ioLoopGroup;
    EventLoopGroup workerGroup;
    Channel channel;

    public Server(ServerConfig config) {
        this.config = config;
    }

    public void start() {
        ioLoopGroup = new NioEventLoopGroup(config.getIoWorkerCount());
        // worker是实际处理数据的线程，为了避免并发操作数据的问题，采用Redis的思路，只保留一个线程来负责处理数据请求
        workerGroup = new NioEventLoopGroup(1);

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.SO_REUSEADDR, true)
                    .childOption(ChannelOption.SO_SNDBUF, 32 * 1024)
                    .childOption(ChannelOption.SO_RCVBUF, 32 * 1024)
                    .childOption(EpollChannelOption.SO_REUSEPORT, true)
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            serverBootstrap.group(ioLoopGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new ServerInitializer());

            channel = serverBootstrap.bind(config.getPort()).channel();
            channel.closeFuture().addListener(future -> {
                stop();
            });
            log.info("zst-cache server started");
        } catch (Exception e) {
            log.error("Exception caught", e);
        }
    }

    public void stop() {
        if (ioLoopGroup != null && !ioLoopGroup.isShutdown()) {
            ioLoopGroup.shutdownGracefully();
            ioLoopGroup = null;
        }

        if (workerGroup != null && !workerGroup.isShutdown()) {
            workerGroup.shutdownGracefully();
            workerGroup = null;
        }
    }

    public static class ServerInitializer extends ChannelInitializer<NioSocketChannel> {

        @Override
        protected void initChannel(NioSocketChannel ch) throws Exception {
            // netty原生自带了RESP协议的编解码器，不过这里还是手写一份
//            ch.pipeline().addLast(new RedisDecoder())
//                    .addLast(new RedisEncoder());
            ch.pipeline().addLast(new RESPDecoder())
                    .addLast(new RESPEncoder())
                    .addLast(new RESPDataAggregator())
                    .addLast(new RESPCommandHandler(CacheFactory.getCache()));
        }
    }
}
