package io.github.gcdd1993.rabbitmq.examples.helloworld;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

/*
 * 发票相关的消息队列
 * */
public class MqReceiveConnection {

/*    //消息队列配置
    private static String HOST_NAME = "";
    private static String USER_NAME = "";
    private static String USER_PASSWORD = "";
    private static String V_HOST = "";*/

    //消息通道信息
//    private static String EXCHANGE_NAME = "";
//    private static String QUEUE_NAME = "";
//    private static String ROUTING_NAME = "";

/*    //实例化一个连接
    private static Connection connection = getConnection();*/

    public static void main(String[] args) {
        Connection connection = getConnection();
        receiveMqMsg(connection, "crm-transfer", "pay-credential-dev-routing", "pay-credential-dev-queue");
        receiveMqMsg(connection, "crm-transfer", "order-create-dev-routing", "order-create-dev-queue");
        receiveMqMsg(connection, "crm-transfer", "crm-order-paid-callback-dev-routing", "crm-order-paid-callback-dev-queue");
//        while (true) {
//            // blocking
//        }
    }

    //接收消息-订单信息
    public static void receiveMqMsg(Connection connection, final String exchange, String routingKey, String queue) {
        try {
            final Channel channelR = connection.createChannel();

            // 创建${ExchangeName}，该Exchange必须在消息队列RabbitMQ版控制台上已存在，并且Exchange的类型与控制台上的类型一致。
            AMQP.Exchange.DeclareOk exchangeDeclareOk = channelR.exchangeDeclare(exchange, "direct", true, false, false, null);
            // 创建${QueueName} ，该Queue必须在消息队列RabbitMQ版控制台上已存在。
            AMQP.Queue.DeclareOk queueDeclareOk = channelR.queueDeclare(queue, true, false, false, new HashMap<String, Object>());
            // Queue与Exchange进行绑定，并确认绑定的BindingKeyTest。
            AMQP.Queue.BindOk bindOk = channelR.queueBind(queue, exchange, routingKey);

            // 开始消费消息。
            channelR.basicConsume(queue, false, "ConsumerTag", new DefaultConsumer(channelR) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    //接收到的消息，进行业务逻辑处理。
                    String receivedMsg = new String(body, StandardCharsets.UTF_8);
                    //处理信息，记录下log
                    System.out.println(queue + "[receiveInfo]： " + new String(body, StandardCharsets.UTF_8) + ", deliveryTag: " + envelope.getDeliveryTag() + ", messageId: " + properties.getMessageId());
                    channelR.basicAck(envelope.getDeliveryTag(), false);
//                    long s = envelope.getDeliveryTag();
//                    System.out.println("test : " + s);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //处理接收到的信息
    public static String msgHandling(String mqtype, String content) {
        String result = "";
        //订单创建
        if (mqtype.equals("order_create")) {
            result = orderInsertToCrm(content);
        }
        //上传转账凭证
        if (mqtype.equals("pay_credential")) {
            result = orderUpdateToCrm(content);
        }
        //支付完成回调
        if (mqtype.equals("crm_order_paid_callback")) {
            result = paidUpdateToCrm(content);
        }
        //log记录一下处理结果
        System.out.println("收到消息：" + mqtype + content);
        return result;
    }

    //订单创建-插入store_orders，store_orders_mingxi
    public static String orderInsertToCrm(String content) {
        return "success";
    }

    //上传转账凭证-更新store_orders,store_orders_mingxi
    public static String orderUpdateToCrm(String content) {
        return "success";
    }

    //支付完成回调-更新store_orders，store_orders_mingxi
    public static String paidUpdateToCrm(String content) {
        return "success";
    }


    /*
     * 通用方法
     * */

    public static Connection getConnection() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            // 设置接入点，在消息队列RabbitMQ版控制台实例详情页面查看。
            factory.setHost("amqp-cn-7mz2gi9q200j.mq-amqp.cn-hangzhou-249959-a.aliyuncs.com");
            // 用户名，在消息队列RabbitMQ版控制台用户名密码管理页面查看。
            factory.setUsername("MjphbXFwLWNuLTdtejJnaTlxMjAwajpMVEFJNXRIdjV5Z1o5aDhnWDdIRFNXRzM=");
            // 密码，在消息队列RabbitMQ版控制台用户名密码管理页面查看。
            factory.setPassword("NkVDQzdDNzU4NTg2REUyMzMzNjVCRDRGNjhGODE4OTdEMzM3M0MzRDoxNjM4MTg0OTQ1MTkx");
            //设置为true，开启Connection自动恢复功能；设置为false，关闭Connection自动恢复功能。
            factory.setAutomaticRecoveryEnabled(true);
            factory.setNetworkRecoveryInterval(5000);
            // 设置Vhost名称，请确保已在消息队列RabbitMQ版控制台上创建完成。
            factory.setVirtualHost("passport");
            // 默认端口，非加密端口5672，加密端口5671。
            factory.setPort(5672);
            // 基于网络环境合理设置超时时间。
            factory.setConnectionTimeout(30 * 1000);
            factory.setHandshakeTimeout(30 * 1000);
            factory.setShutdownTimeout(0);
            Connection connection = factory.newConnection();
            return connection;
        } catch (Exception e) {
            return null;
        }
    }

    //初始化通道配置信息
    public static List getMqSetting(String mqtype) {
        return List.of();
    }
/*    public static void initMqSetting(String mqtype) {
        String sql = "select exchange,routing,queue from mq_invoice_setting " +
                "where mqtype = '" + mqtype + "'";
        List setList = JDBCTool.getColvalues("CRM", sql);
        if (setList.size() > 0) {
            Map rowData = (Map) setList.get(0);
            EXCHANGE_NAME = rowData.get("exchange").toString();
            ROUTING_NAME = rowData.get("routing").toString();
            QUEUE_NAME = rowData.get("queue").toString();
        }
    }*/

/*    //初始化mq信息
    public static void initMqInfo() {
        String sql = "select host,user,password,vhost from mq_account_info where type = 'jiushuyun'";
        List setList = JDBCTool.getColvalues("CRM", sql);
        if (setList.size() > 0) {
            Map rowData = (Map) setList.get(0);
            HOST_NAME = rowData.get("host").toString();
            USER_NAME = rowData.get("user").toString();
            USER_PASSWORD = rowData.get("password").toString();
            V_HOST = rowData.get("vhost").toString();
        }
    }*/

}
