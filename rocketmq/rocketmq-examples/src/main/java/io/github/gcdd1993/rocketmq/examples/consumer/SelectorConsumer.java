package io.github.gcdd1993.rocketmq.examples.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.Message;

/**
 * @author <a href="mailto:gcwm99@gmail.com">gcdd1993</a>
 * @since 2022/2/28
 */
public class SelectorConsumer {

    public static void main(String[] args) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("selector_group");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("shutdown consumer...");
            consumer.shutdown();
        }));
        try {
            consumer.setNamesrvAddr("localhost:9876");
            // 只有订阅的消息有这个属性a, a >=0 and a <= 3
            consumer.subscribe("TopicTest", MessageSelector.bySql("a between 0 and 3"));
            consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                        System.out.println("msg size: " + msgs.size());
                        for (Message msg : msgs) {
                            System.out.println(msg);
                        }
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
            );
            consumer.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
