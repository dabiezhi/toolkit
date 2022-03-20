package com.bloom.tim.client.init;

import javax.annotation.PostConstruct;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * Created by on 2022-03-20 21:12
 */
@Component
@Slf4j
public class TimClient {
    private EventLoopGroup group = new NioEventLoopGroup(0, new DefaultThreadFactory("tim-work"));

    private SocketChannel socketChannel;

    @PostConstruct
    public void start() {
        startClient();
    }

    private void startClient() {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new TimClientHandlerInitializer());

        ChannelFuture future = null;
        try {
            future = bootstrap.connect("127.0.0.1", 60001).sync();
        } catch (Exception e) {
            log.error("Connect fail!", e);
        }
        if (null == future || future.isSuccess()) {
            log.info("启动 tim client 成功");
        } else {
            socketChannel = (SocketChannel) future.channel();
        }
    }
}