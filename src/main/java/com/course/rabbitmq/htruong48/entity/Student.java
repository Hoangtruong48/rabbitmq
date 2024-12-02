package com.course.rabbitmq.htruong48.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("birth_day")
    private Long birthDay;
    @JsonProperty("gender")
    private Boolean gender;
    @JsonProperty("cap_hoc")
    private String capHoc;
}
