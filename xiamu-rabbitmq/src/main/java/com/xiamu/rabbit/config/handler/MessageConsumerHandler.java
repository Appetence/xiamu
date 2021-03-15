package com.xiamu.rabbit.config.handler;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 消费者
 * @Auther: : xiamu
 * @Date: 2019/11/26 14:38
 * @company：CTTIC
 */
@Component
public class MessageConsumerHandler implements ChannelAwareMessageListener {

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 1、处理成功，这种时候用basicAck确认消息；
     * 2、可重试的处理失败，这时候用basicNack将消息重新入列；
     * 3、不可重试的处理失败，这时候使用basicNack将消息丢弃。
     * <p>
     * basicNack(long deliveryTag, boolean multiple, boolean requeue)
     * deliveryTag:该消息的index  RabbitMQ 向该 Channel 投递的这条消息的唯一标识 ID，是一个单调递增的正整数，delivery tag 的范围仅限于 Channel
     * multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
     * requeue：被拒绝的是否重新入队列
     */
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        byte[] body = message.getBody();
        logger.info("接收到消息:" + new String(body));
        JSONObject jsonObject = null;
        try {
//            jsonObject = JSONObject.parseObject(new String(body));
            if (true) {
                //确认消息消费成功
                logger.info("消息消费成功");
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } else if (true) {
                //消费失败重新入队列消费
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } else {
                //消费失败，丢弃消息
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            }
        } catch (Exception e) {
            //消息丢弃
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            logger.error("This message:" + jsonObject + " conversion JSON error ");
        }
    }

}
