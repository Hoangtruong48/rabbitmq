package com.course.rabbitmq.htruong48.service;

import com.course.rabbitmq.htruong48.entity.Human;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.UUID;

//@Service

public class FanoutExchangeRabbitMqService {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

//    @Autowired
    public FanoutExchangeRabbitMqService(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendHuman() throws JsonProcessingException {
        int ii = 10;
        for (int i = 0; i < ii ;i++){
            Human human = Human.builder()
                    .age(18)
                    .id(UUID.randomUUID().toString())
                    .name("Human" + i)
                    .humanCode("code" + "h" + i)
                    .build();
            String jsonString = objectMapper.writeValueAsString(human);
            log.info("Human i = {}, value = {}", i, jsonString);
            rabbitTemplate.convertAndSend("x.hr", "", jsonString);
        }
    }
}
