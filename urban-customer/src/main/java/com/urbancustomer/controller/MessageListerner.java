package com.urbancustomer.controller;

import com.urbancustomer.config.MQConfig;
import com.urbancustomer.entity.CustomerMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListerner {

    @RabbitListener(queues = MQConfig.QUEUE_CUSTOMER)
    public void listener(CustomerMessage message){
        System.out.println(message);
    }
}
