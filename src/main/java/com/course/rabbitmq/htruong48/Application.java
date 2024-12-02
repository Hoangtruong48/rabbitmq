package com.course.rabbitmq.htruong48;

import com.course.rabbitmq.htruong48.service.SendMessageUsingJackson2Json;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.course.rabbitmq.htruong48")
public class Application implements CommandLineRunner {

	private final SendMessageUsingJackson2Json service;
	public Application(SendMessageUsingJackson2Json service) {
		this.service = service;
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws JsonProcessingException {
		service.sendStudentTest();
	}
}
