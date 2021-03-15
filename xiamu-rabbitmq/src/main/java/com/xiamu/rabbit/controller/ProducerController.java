package com.xiamu.rabbit.controller;

import com.xiamu.rabbit.config.handler.QueueService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Properties;

/**
 * @Description: TODO
 * @Auther: : xiamu
 * @Date: 2019/11/25 21:03
 * @company：CTTIC
 */
@RestController
@AllArgsConstructor
public class ProducerController {
    private static Logger logger = LoggerFactory.getLogger(ProducerController.class);
    private RabbitTemplate rabbit;

    private RabbitAdmin rabbitAdmin;
    @Autowired
    private QueueService queueService;
    @Autowired
    private SimpleMessageListenerContainer autoSimpleMessageListenerContainer;




    @GetMapping("/queue_test")
    public String produce(String queueName, String message) {
        createMQIfNotExist(queueName, queueName);
        rabbit.convertAndSend(queueName, message);
        return "消息已经发送";
    }

    private void createMQIfNotExist(String queueName, String exchangeName) {
        //判断队列是否存在
        Properties properties = rabbitAdmin.getQueueProperties(queueName);
        if (properties == null) {
            Queue queue = new Queue(queueName, true, false, false, null);
            FanoutExchange fanoutExchange = new FanoutExchange(exchangeName);
            rabbitAdmin.declareQueue(queue);
            rabbitAdmin.declareExchange(fanoutExchange);
            rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(fanoutExchange));
            addNewListener(queueName);

        }


    }
    public String addNewListener(String queueName){
        List<String> queueNameList = queueService.getMQJSONArray();
        int count = 0;
        while(!queueNameList.contains(queueName)){
            //这里可以改成用加锁的方式判断
            queueNameList = queueService.getMQJSONArray();
            count++;
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            if(count >=5){
                return "动态添加监听失败";
            }
        }
        autoSimpleMessageListenerContainer.addQueueNames(queueName);
        long consumerCount = autoSimpleMessageListenerContainer.getActiveConsumerCount();
        return "修改成功:现有队列监听者["+consumerCount+"]个";
    }


}
