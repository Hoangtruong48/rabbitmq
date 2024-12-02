package com.course.rabbitmq.htruong48.config;

import com.course.rabbitmq.htruong48.constant.RabbitMqName;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqConfig {
    // Khai báo các tham số cho queue
    public static Map<String, Object> args;

    static {
        args = new HashMap<>();
        // Thêm các tham số DLX vào Map args
        args.put("x-dead-letter-exchange", RabbitMqName.DEAD_LETTER_EXCHANGE);  // Chỉ định DLX Exchange
        args.put("x-dead-letter-routing-key", RabbitMqName.DLT_ROUTINGKEY);  // Chỉ định Routing Key cho DLX
        args.put("x-message-ttl", 30000);  // (Tùy chọn) Thời gian sống của message (TTL) = 24 giờ
    }
    @Bean
    ObjectMapper objectMapper() {
        return JsonMapper.builder().findAndAddModules().build();
    }
    @Bean
    Jackson2JsonMessageConverter converter(ObjectMapper objectMapper){
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    Queue STUDENT_C1_QUEUE() {
        return new Queue(RabbitMqName.STUDENT_C1_QUEUE, true, false, false, args);
    }

    @Bean
    Queue STUDENT_C2_QUEUE() {
        return new Queue(RabbitMqName.STUDENT_C2_QUEUE, true, false, false, args);
    }

    @Bean
    Queue STUDENT_C3_QUEUE() {
        return new Queue(RabbitMqName.STUDENT_C3_QUEUE, true, false, false, args);
    }

    // DLT queue
    @Bean
    Queue DLT_QUEUE(){
        return new Queue(RabbitMqName.DEAD_LETTER_QUEUE, true, false, false);
    }
    // DLT Exchange
    @Bean
    TopicExchange DLT_EXCHANGE(){
        return new TopicExchange(RabbitMqName.DEAD_LETTER_EXCHANGE, true, false);
    }
    //
    @Bean
    TopicExchange STUDENT_EXCHANGE() {
        return new TopicExchange(RabbitMqName.STUDENT_EXCHANGE, true, false, args);
    }

    // Binding queue
    @Bean
    Binding binding_student_c1_queue(Queue STUDENT_C1_QUEUE, TopicExchange STUDENT_EXCHANGE) {
        return BindingBuilder.bind(STUDENT_C1_QUEUE).to(STUDENT_EXCHANGE).with(RabbitMqName.CAP1);
    }

    @Bean
    Binding binding_student_c2_queue(Queue STUDENT_C2_QUEUE, TopicExchange STUDENT_EXCHANGE) {
        return BindingBuilder.bind(STUDENT_C2_QUEUE).to(STUDENT_EXCHANGE).with(RabbitMqName.CAP2);
    }

    @Bean
    Binding binding_student_c3_queue(Queue STUDENT_C3_QUEUE, TopicExchange STUDENT_EXCHANGE) {
        return BindingBuilder.bind(STUDENT_C3_QUEUE).to(STUDENT_EXCHANGE).with(RabbitMqName.CAP3);
    }

    @Bean
    Binding binding_dlt_queue(Queue DLT_QUEUE, TopicExchange DLT_EXCHANGE){
        return BindingBuilder.bind(DLT_QUEUE).to(DLT_EXCHANGE).with(RabbitMqName.DLT_ROUTINGKEY);
    }
}
