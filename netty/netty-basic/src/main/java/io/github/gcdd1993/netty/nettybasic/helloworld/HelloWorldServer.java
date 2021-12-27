package io.github.gcdd1993.netty.nettybasic.helloworld;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author gcdd1993
 * @since 2021/12/27
 */
public class HelloWorldServer {

    public static void main(String[] args) {
        new ServerBootstrap()
                .group(new NioEventLoopGroup()) // 1. 创建 NioEventLoopGroup，可以简单理解为 `线程池 + Selector`
                .channel(NioServerSocketChannel.class) // 2. 选择服务 Socket 实现类，其中 NioServerSocketChannel 表示基于 NIO 的服务器端实现
                .childHandler(new ChannelInitializer<NioSocketChannel>() { // 3. 接下来添加的处理器都是给 SocketChannel 用的，而不是给 ServerSocketChannel
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception { // 连接建立后调用
                        ch.pipeline()
                                .addLast(new StringDecoder()) // 5. SocketChannel 的处理器，解码 ByteBuf => String
                                .addLast(new SimpleChannelInboundHandler<String>() { // 6. SocketChannel 的业务处理器，使用上一个处理器的处理结果
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                        System.out.println(msg);
                                    }
                                });
                    }
                })
                .bind(8080); // 4. ServerSocketChannel 绑定的监听端口
    }
}
