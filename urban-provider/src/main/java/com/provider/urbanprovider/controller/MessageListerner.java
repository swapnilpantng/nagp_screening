package com.provider.urbanprovider.controller;

import com.provider.urbanprovider.config.MQConfig;
import com.provider.urbanprovider.enity.CustomMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListerner {

    @RabbitListener(queues = MQConfig.QUEUE_PROVIDER)
    public void listener(CustomMessage message){
        System.out.println(message);
    }
}
