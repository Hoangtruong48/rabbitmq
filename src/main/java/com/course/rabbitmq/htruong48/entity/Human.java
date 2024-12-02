package com.course.rabbitmq.htruong48.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Human {
    private String id;
    private String name;
    private Integer age;
    private String humanCode;
}
