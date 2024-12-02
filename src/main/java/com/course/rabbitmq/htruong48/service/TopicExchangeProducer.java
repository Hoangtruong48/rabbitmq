package com.course.rabbitmq.htruong48.service;

import com.course.rabbitmq.htruong48.constant.RabbitMqName;
import com.course.rabbitmq.htruong48.entity.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Arrays;
import java.util.List;

//@Service
public class TopicExchangeProducer {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final List<String> CAP_HOC_LEVEL = Arrays.asList("LEVEL1", "LEVEL2", "LEVEL3");
//    @Autowired
    public TopicExchangeProducer(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendStudent() throws JsonProcessingException {
        int ii = 15;
        for (int i = 0; i < ii; ++i){
            Student student = Student.builder()
                    .id("student" + i)
                    .name("student" + i)
                    .gender(i % CAP_HOC_LEVEL.size() == 0 ? Boolean.TRUE : Boolean.FALSE)
                    .capHoc(CAP_HOC_LEVEL.get(i % CAP_HOC_LEVEL.size()))
                    .build();
            String jsonStudent = objectMapper.writeValueAsString(student);
            rabbitTemplate.convertAndSend(RabbitMqName.STUDENT_EXCHANGE, student.getCapHoc(), jsonStudent);
        }
    }

}
