package com.course.rabbitmq.htruong48.constant;

public class RabbitMqName {
    // Queue
    public static final String STUDENT_QUEUE = "student_queue";
    public static final String STUDENT_C1_QUEUE = "student_c1";
    public static final String STUDENT_C2_QUEUE = "student_c2";
    public static final String STUDENT_C3_QUEUE = "student_c3";
    public static final String DEAD_LETTER_QUEUE = "dead_letter_queue";
    // Exchange
    public static final String STUDENT_EXCHANGE = "student_exchange";
    public static final String DEAD_LETTER_EXCHANGE = "dead_letter_exchange";
    //  rouing key
    public static final String CAP1 = "LEVEL1";
    public static final String CAP2 = "LEVEL2";
    public static final String CAP3 = "LEVEL3";
    public static final String DLT_ROUTINGKEY = "dlt_key";
}
