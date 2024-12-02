package com.course.rabbitmq.htruong48.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service

public class HelloRabbitMqService {
    private final Logger LOG = LoggerFactory.getLogger(HelloRabbitMqService.class);
    private final RabbitTemplate rabbitTemplate;
    public HelloRabbitMqService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void send() {
        rabbitTemplate.convertAndSend("test", "Htruong48");
        LOG.info("message = {}", "Htruong48");
    }
}
