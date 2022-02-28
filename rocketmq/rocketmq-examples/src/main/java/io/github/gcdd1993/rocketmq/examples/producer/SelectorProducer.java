package io.github.gcdd1993.rocketmq.examples.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @author <a href="mailto:gcwm99@gmail.com">gcdd1993</a>
 * @since 2022/2/28
 */
public class SelectorProducer {

    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("selector_group");
        producer.setNamesrvAddr("localhost:9876");
        try {
            producer.start();
            for (int i = 0; i < 100; i++) {
                Message msg = new Message("TopicTest",
                        "one_tag",
                        ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
                );
                // 设置一些属性
                msg.putUserProperty("a", String.valueOf(i));
                SendResult sendResult = producer.send(msg);

                System.out.println(sendResult);
            }

        } catch (InterruptedException | RemotingException | MQClientException | MQBrokerException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }
}
