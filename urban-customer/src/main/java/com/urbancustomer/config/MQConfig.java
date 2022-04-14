package com.urbancustomer.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("customer")
public class MQConfig {
    public static final String QUEUE_CUSTOMER = "message_queue_customer";
    public static final String QUEUE_PROVIDER = "message_queue_provider";
    public static final String EXCHANGE = "message_exchange";

    @Bean
    public Queue queueCustomer() {
        return new Queue(QUEUE_CUSTOMER );
    }

    @Bean
    public Queue queueProvider() {
        return new Queue(QUEUE_PROVIDER);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding bindingCustomer(@Qualifier("queueCustomer") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(QUEUE_CUSTOMER);
    }

    @Bean
    public Binding bindingProvider(@Qualifier("queueProvider") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(QUEUE_PROVIDER);
    }

    @Bean
    public MessageConverter messageConverterCustomer() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate templateCustomer(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate((connectionFactory));
        template.setMessageConverter(messageConverterCustomer());
        return template;
    }
}
