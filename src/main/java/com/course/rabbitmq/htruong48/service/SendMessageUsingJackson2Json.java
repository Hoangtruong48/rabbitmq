package com.course.rabbitmq.htruong48.service;

import com.course.rabbitmq.htruong48.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.course.rabbitmq.htruong48.constant.RabbitMqName.STUDENT_EXCHANGE;

@Service
public class SendMessageUsingJackson2Json {
    private final List<String> CAP_HOC_LEVEL = Arrays.asList("LEVEL1", "LEVEL2", "LEVEL3");
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public SendMessageUsingJackson2Json(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendStudentTest() {
        int ii = 15;
        for (int i = 0; i < ii; ++i) {
            Student student = Student.builder()
                    .id("student" + i)
                    .name("student" + i)
                    .gender(i % CAP_HOC_LEVEL.size() == 0 ? Boolean.TRUE : Boolean.FALSE)
                    .capHoc(CAP_HOC_LEVEL.get(i % CAP_HOC_LEVEL.size()))
                    .build();
            rabbitTemplate.convertAndSend(STUDENT_EXCHANGE, student.getCapHoc(), student);
            log.info("Student {} = {}", i, student);
        }
    }
}
