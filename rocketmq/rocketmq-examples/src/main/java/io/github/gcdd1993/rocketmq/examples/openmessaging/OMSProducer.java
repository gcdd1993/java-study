package io.github.gcdd1993.rocketmq.examples.openmessaging;

import io.openmessaging.Future;
import io.openmessaging.Message;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.OMS;
import io.openmessaging.producer.Producer;
import io.openmessaging.producer.SendResult;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

/**
 * OpenMessaging样例
 *
 * @author <a href="mailto:gcwm99@gmail.com">gcdd1993</a>
 * @since 2022/2/28
 */
public class OMSProducer {

    public static void main(String[] args) {
        final MessagingAccessPoint messagingAccessPoint = OMS.getMessagingAccessPoint("oms:rocketmq://192.168.2.49:9876/default:default");
        final Producer producer = messagingAccessPoint.createProducer();

        messagingAccessPoint.startup();
        System.out.println("MessagingAccessPoint startup OK");
        producer.startup();
        System.out.println("Producer startup OK");

        Message message = producer.createBytesMessage("OMS_HELLO_TOPIC", "OMS_HELLO_BODY".getBytes(StandardCharsets.UTF_8));
        SendResult sendResult = producer.send(message);
        //final Void aVoid = result.get(3000L);
        System.out.printf("Send async message OK, msgId: %s%n", sendResult.messageId());

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        final Future<SendResult> result = producer.sendAsync(producer.createBytesMessage("OMS_HELLO_TOPIC", "OMS_HELLO_BODY".getBytes(StandardCharsets.UTF_8)));
        result.addListener(future -> {
            if (future.getThrowable() != null) {
                System.out.printf("Send async message Failed, error: %s%n", future.getThrowable().getMessage());
            } else {
                System.out.printf("Send async message OK, msgId: %s%n", future.get().messageId());
            }
            countDownLatch.countDown();
        });

        producer.sendOneway(producer.createBytesMessage("OMS_HELLO_TOPIC", "OMS_HELLO_BODY".getBytes(StandardCharsets.UTF_8)));
        System.out.printf("Send oneway message OK%n");

        try {
            countDownLatch.await();
            Thread.sleep(500); // 等一些时间来发送消息
        } catch (InterruptedException ignore) {
        }
        producer.shutdown();
    }
}
