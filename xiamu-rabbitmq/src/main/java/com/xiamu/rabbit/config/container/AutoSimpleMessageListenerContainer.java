package com.xiamu.rabbit.config.container;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.impl.AMQConnection;
import com.rabbitmq.client.impl.FrameHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

import java.util.Map;
import java.util.UUID;

/**
 * @author 公共
 */
public class AutoSimpleMessageListenerContainer extends SimpleMessageListenerContainer {

    /**
     * MQ连接端口
     */
    public static final String MQ_PORT = "mq.port";
    /**
     * MQ IP
     */
    public static final String MQ_ADDRESS = "mq.address";
    /**
     * 本地IP
     */
    public static final String MQ_LOCALADDRESS = "mq.localAddress";

    /**
     * 消息附带日志
     */
    public static final String MQ_LOG = "mq.log";


    protected void invokeListener(Channel channel, Message message) throws Exception {
        Map<String, Object> headers = message.getMessageProperties().getHeaders();

        AMQConnection connection = (AMQConnection) channel.getConnection();

        FrameHandler frameHandler = connection.getFrameHandler();
        headers.put(MQ_ADDRESS, frameHandler.getAddress().getHostAddress());
        headers.put(MQ_PORT, frameHandler.getPort());
        headers.put(MQ_LOCALADDRESS, frameHandler.getLocalAddress().getHostAddress());

        String log = (String) headers.get(MQ_LOG);

        String msgId = message.getMessageProperties().getMessageId();

        if (StringUtils.isBlank(msgId)) {
            message.getMessageProperties().setMessageId(StringUtils.remove(UUID.randomUUID().toString(), "-"));
        }

//        MdcLog.setMdcConfig(log, "rabbit");

        super.invokeListener(channel, message);

//        MdcLog.clear();
    }

    public AutoSimpleMessageListenerContainer() {
        super();
    }

    public AutoSimpleMessageListenerContainer(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }
}