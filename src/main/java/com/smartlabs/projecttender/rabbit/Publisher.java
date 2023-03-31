package com.smartlabs.projecttender.rabbit;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
public class Publisher {

    @Value("${exchange.topic.name}")
    String topicExchangeName;

    final RabbitTemplate rabbitTemplate;

    public Publisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(Object message, String routingKey) {
        rabbitTemplate.convertAndSend(topicExchangeName, routingKey, message);
    }
}
