package com.example.tim.server.init;

import com.example.tim.server.handler.TimServerHandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author Administrator
 * Created by on 2022-03-20 21:08
 */
public class TimServerHandlerInitializer extends ChannelInitializer<Channel> {

    private final TimServerHandler timServerHandler = new TimServerHandler();

    @Override
    protected void initChannel(Channel channel) throws Exception {
        //10秒没发送消息 将IdleStateHandler添加到ChannelPipeline中
        channel.pipeline().addLast(new IdleStateHandler(11, 0, 0))
                .addLast(timServerHandler);
    }
}