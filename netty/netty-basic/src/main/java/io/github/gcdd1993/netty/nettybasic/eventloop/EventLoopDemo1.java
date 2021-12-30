package io.github.gcdd1993.netty.nettybasic.eventloop;

import io.netty.channel.DefaultEventLoopGroup;
import io.netty.util.concurrent.EventExecutor;

/**
 * EventLoopGroup 是一组 EventLoop，Channel 一般会调用 EventLoopGroup 的 register 方法来绑定其中一个 EventLoop，
 * 后续这个 Channel 上的 io 事件都由此 EventLoop 来处理（保证了 io 事件处理时的线程安全）
 *
 * @author gcdd1993
 * @since 2021/12/27
 */
public class EventLoopDemo1 {
    public static void main(String[] args) {
        // 内部创建了两个 EventLoop, 每个 EventLoop 维护一个线程
        DefaultEventLoopGroup group = new DefaultEventLoopGroup(2); // 普通任务，定时任务
        // 2. 获取下一个事件循环对象（轮询获取）
        // io.netty.channel.DefaultEventLoop@44f75083
        // io.netty.channel.DefaultEventLoop@2698dc7
        // io.netty.channel.DefaultEventLoop@44f75083
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());

        // 也可以使用 for 循环
        for (EventExecutor eventExecutor : group) {
            System.out.println(eventExecutor);
        }
    }
}
