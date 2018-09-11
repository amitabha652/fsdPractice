package com.cts.fsd.tasktracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TasktrackerApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/tasktrackerbackend");
		System.setProperty("spring.datasource.url", "jdbc:h2:.\\src\\main\\resources\\DB\\tasktracker");
		SpringApplication.run(TasktrackerApplication.class, args);
	}
}
