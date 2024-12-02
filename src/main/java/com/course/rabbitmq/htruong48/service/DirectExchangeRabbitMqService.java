package com.course.rabbitmq.htruong48.service;

import com.course.rabbitmq.htruong48.entity.Image;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

//@Service
public class DirectExchangeRabbitMqService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
//    @Autowired
    public DirectExchangeRabbitMqService(RabbitTemplate rabbitTemplate,
                                         ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }
    public static final List<String> types = Arrays.asList("png", "jpg", "vector");
    public void sendImage() throws JsonProcessingException {
        int ii = 20;
        for (int i = 0; i < ii; i++){
            String type = types.get(i % types.size());
            Image image = Image.builder()
                    .id(UUID.randomUUID().toString())
                    .description("Test " + i)
                    .type(type)
                    .build();
            String jsonString = objectMapper.writeValueAsString(image);
            rabbitTemplate.convertAndSend("x.image", type, jsonString);
        }
    }
}
