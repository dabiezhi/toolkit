package com.bloom.tim.client.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author taosy
 * @version V1.0
 * @since 2021-12-18 23:18
 */
@Slf4j
@ChannelHandler.Sharable
public class TimClientHandler extends SimpleChannelInboundHandler<Object> {

    private ChannelHandlerContext context;

    //处理服务端返回的数据
    @Override
    protected synchronized void channelRead0(ChannelHandlerContext ctx, Object obj) throws Exception {
        log.info("Netty客户端接收服务端响应数据数据-进行中..: " + obj.toString());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("tim server connect success!");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

}
