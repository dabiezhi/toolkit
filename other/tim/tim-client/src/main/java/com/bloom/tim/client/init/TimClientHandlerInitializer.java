package com.bloom.tim.client.init;

import com.bloom.tim.client.handler.TimClientHandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author Administrator
 * Created by on 2022-03-20 21:08
 */
public class TimClientHandlerInitializer extends ChannelInitializer<Channel> {

    private final TimClientHandler timClientHandler = new TimClientHandler();

    @Override
    protected void initChannel(Channel channel) throws Exception {
        //10秒没发送消息 将IdleStateHandler添加到ChannelPipeline中
        channel.pipeline().addLast(new IdleStateHandler(0, 10, 0))
                .addLast(timClientHandler);
    }
}