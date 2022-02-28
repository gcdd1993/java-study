package io.github.gcdd1993.rocketmq.examples.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 发送批量消息
 *
 * @author <a href="mailto:gcwm99@gmail.com">gcdd1993</a>
 * @since 2022/2/28
 */
public class BatchMessageProducer {

    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("batch_message_group");
        try {
            producer.setNamesrvAddr("localhost:9876");
            producer.start();

            String topic = "BatchTest";
            List<Message> messageList = new ArrayList<>();
            messageList.add(new Message(topic, "TagA", "OrderID001", "Hello world 0".getBytes()));
            messageList.add(new Message(topic, "TagA", "OrderID002", "Hello world 1".getBytes()));
            messageList.add(new Message(topic, "TagA", "OrderID003", "Hello world 2".getBytes()));

            //把大的消息分裂成若干个小的消息
            ListSplitter splitter = new ListSplitter(messageList);
            while (splitter.hasNext()) {
                List<Message> listItem = splitter.next();
                producer.send(listItem);
            }
        } catch (MQClientException | InterruptedException | RemotingException | MQBrokerException e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }

    /**
     * 消息列表分割
     * <p>
     * 复杂度只有当你发送大批量时才会增长，你可能不确定它是否超过了大小限制（4MB）。这时候你最好把你的消息列表分割一下
     */
    static class ListSplitter implements Iterator<List<Message>> {

        private final int SIZE_LIMIT = 1024 * 1024 * 4;
        private final List<Message> messages;
        private int currIndex;

        public ListSplitter(List<Message> messages) {
            this.messages = messages;
        }

        @Override
        public boolean hasNext() {
            return currIndex < messages.size();
        }

        @Override
        public List<Message> next() {
            int startIndex = getStartIndex();
            int nextIndex = startIndex;
            int totalSize = 0;
            for (; nextIndex < messages.size(); nextIndex++) {
                Message message = messages.get(nextIndex);
                int tmpSize = calcMessageSize(message);
                if (tmpSize + totalSize > SIZE_LIMIT) {
                    break;
                } else {
                    totalSize += tmpSize;
                }
            }
            List<Message> subList = messages.subList(startIndex, nextIndex);
            currIndex = nextIndex;
            return subList;
        }

        private int getStartIndex() {
            Message currMessage = messages.get(currIndex);
            int tmpSize = calcMessageSize(currMessage);
            while (tmpSize > SIZE_LIMIT) {
                currIndex += 1;
                Message message = messages.get(currIndex);
                tmpSize = calcMessageSize(message);
            }
            return currIndex;
        }

        private int calcMessageSize(Message message) {
            int tmpSize = message.getTopic().length() + message.getBody().length;
            Map<String, String> properties = message.getProperties();
            for (Map.Entry<String, String> entry : properties.entrySet()) {
                tmpSize += entry.getKey().length() + entry.getValue().length();
            }
            tmpSize = tmpSize + 20; // 增加⽇日志的开销20字节
            return tmpSize;
        }
    }
}
