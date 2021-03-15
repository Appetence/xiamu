package com.xiamu.rabbit.config;

import com.xiamu.rabbit.config.container.AutoSimpleMessageListenerContainer;
import com.xiamu.rabbit.config.handler.MessageConsumerHandler;
import com.xiamu.rabbit.config.handler.QueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;

import java.io.IOException;
import java.util.List;

/**
 * @Description: TODO
 * @Auther: : xiamu
 * @Date: 2019/11/25 20:25
 * @company：CTTIC
 */
@Configuration
@Slf4j
public class RabbitmqConfig {

    @Autowired
    private RabbitmqProperties rabbitmqProperties;
    /**
     * 消费者
     */
    @Autowired
    private MessageConsumerHandler handler;
    /**
     * base
     */
    @Autowired
    private QueueService queueService;

    /**
     * 创建mq连接
     *
     * @return
     */
    @Bean(name = "connectionFactory")
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();

        connectionFactory.setUsername(rabbitmqProperties.username);
        connectionFactory.setPassword(rabbitmqProperties.password);
        connectionFactory.setVirtualHost(rabbitmqProperties.mqRabbitVirtualHost);
        //若使用confirm-callback或return-callback，必须要配置publisherConfirms或publisherReturns为true
        //每个rabbitTemplate只能有一个confirm-callback和return-callback，如果这里配置了，那么写生产者的时候不能再写confirm-callback和return-callback

        /**
         * 考虑到并发性，与 validErr 消息的 次要性，这里不使用 confirm 模式 和 return 模式
         * 如果使用这两个模式的话，会报异常 channelMax reached。
         * 如果后边需要这两个模式的话，再做解决，可以考虑通过Thread.sleep() 的方式，减少 channel 的积压
         */
        //旧版本使用
        //connectionFactory.setPublisherConfirms(true);
        // rabbitMq新版本开启 ReturnCallback
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        //ConfirmCallback：消息发送成功的回调当消息进入Exchange交换器时就进入回调,不管是否进入队列。默认是关闭的需要手动开启在配置ConnectionFactory连接对象时配置
        connectionFactory.setPublisherReturns(true);

        //该方法配置多个host，在当前连接host down掉的时候会自动去重连后面的host
        connectionFactory.setAddresses(rabbitmqProperties.address);
        return connectionFactory;
    }

    @Bean
    @Scope("prototype")
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        //使用return-callback时必须设置mandatory为true，或者在配置中设置mandatory-expression的值为true
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        /**
         * Mandatory：
         *
         * 为true时,消息通过交换器无法匹配到队列会返回给生产者 并触发MessageReturn
         *
         * 为false时,匹配不到会直接被丢弃
         */
        rabbitTemplate.setMandatory(true);
        /**
         *
         * 如果消息没有到exchange,则confirm回调,ack=false
         * 如果消息到达exchange,则confirm回调,ack=true
         * exchange到queue成功,则不回调return
         * exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
         */
        //消息发送成功的回调
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (ack) {
                    log.debug("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause);
                } else {
                    log.debug("消息发送失败:correlationData({}),ack({}),cause({})", correlationData, ack, cause);
                }
            }
        });
        //发生异常时的消息返回提醒
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                log.debug("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message);
            }
        });
        return rabbitTemplate;
    }

    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory());
    }


    @Bean
    @Order(value = 2)
    public AutoSimpleMessageListenerContainer mqMessageContainer(CachingConnectionFactory connectionFactory) throws AmqpException, IOException {
        AutoSimpleMessageListenerContainer container = new AutoSimpleMessageListenerContainer(connectionFactory);
        List<String> list = queueService.getMQJSONArray();
        container.setQueueNames(list.toArray(new String[list.size()]));
        //设置监听外露
        container.setExposeListenerChannel(true);
        //设置每个消费者获取的最大的消息数量
        container.setPrefetchCount(rabbitmqProperties.getPrefetchCount());
        //消费者个数
        container.setConcurrentConsumers(rabbitmqProperties.getConcurrentConsumers());
        //监听处理类
        container.setMessageListener(handler);
        //设置是否重回队列
        container.setDefaultRequeueRejected(true);
        //设置手动签收 --》 这里指定确认方式 Auto为自动  MANUAL为手动
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }
}
