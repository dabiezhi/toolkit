package com.example.tim.server.handler;

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
public class TimServerHandler extends SimpleChannelInboundHandler<Object> {

    /**
     * 取消绑定
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //可能出现业务判断离线后再次触发 channelInactive
        ctx.channel().close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("received msg=[{}]", msg.toString());
        ctx.writeAndFlush("谢谢你!");

    }


}