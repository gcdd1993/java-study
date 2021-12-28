package io.github.gcdd1993.netty.nettybasic.channel;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author gcdd1993
 * @since 2021/12/28
 */
public class ChannelFutureDemo {

    public static void main(String[] args) throws InterruptedException {
        // 返回的是 ChannelFuture 对象，它的作用是利用 channel() 方法来获取 Channel 对象
        ChannelFuture channelFuture = new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new StringDecoder());
                    }
                })
                .connect("localhost", 8080);

        System.out.println(channelFuture.channel()); // 执行到 1 时，连接未建立，打印 [id: 0x7cd2f01b]
        channelFuture.sync(); // 执行到 2 时，sync 方法是同步等待连接建立完成
        System.out.println(channelFuture.channel()); // 执行到 3 时，连接肯定建立了，打印 [id: 0x7cd2f01b, L:/127.0.0.1:62194 - R:localhost/127.0.0.1:8080]
//        channelFuture.sync().channel().writeAndFlush(new Date() + ": hello world!");
    }
}
