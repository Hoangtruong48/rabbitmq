package com.course.rabbitmq.htruong48.service;

import com.course.rabbitmq.htruong48.entity.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class JsonRabbitMqTest {
    private final ObjectMapper mapper;
    private final RabbitTemplate rabbitTemplate;
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    public JsonRabbitMqTest(ObjectMapper mapper, RabbitTemplate rabbitTemplate) {
        this.mapper = mapper;
        this.rabbitTemplate = rabbitTemplate;
    }
    public void sendStudent() throws JsonProcessingException {
        int ii = 5;
        for (int i = 0; i < ii; i++) {
            Student student = Student.builder()
                    .id("student" + i)
                    .birthDay(System.currentTimeMillis())
                    .gender(Boolean.FALSE)
                    .name("Student" + i)
                    .build();
            String jsonString = mapper.writeValueAsString(student);
            rabbitTemplate.convertAndSend("students-test", jsonString);
        }

    }


}
