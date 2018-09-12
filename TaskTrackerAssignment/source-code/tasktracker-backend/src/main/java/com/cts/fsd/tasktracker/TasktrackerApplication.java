package com.cts.fsd.tasktracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TasktrackerApplication {
	public static void main(String[] args) {

//		System.setProperty("key", "value");
		
		System.setProperty("server.servlet.context-path", "/tasktrackerbackend");
		System.setProperty("server.port", "8080");
		
		System.setProperty("spring.datasource.name", "task_db");
		System.setProperty("spring.datasource.url", "jdbc:h2:file:.\\src\\main\\resources\\DB\\tasktracker;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
		System.setProperty("spring.datasource.username", "SA");
		System.setProperty("spring.datasource.password", "");
		System.setProperty("spring.datasource.driverClassName", "org.h2.Driver");
		System.setProperty("spring.datasource.initialize", "true");
		
		System.setProperty("spring.jpa.generate-ddl", "true");
		System.setProperty("spring.jpa.hibernate.ddl-auto", "update");
		System.setProperty("spring.jpa.database-platform", "org.hibernate.dialect.H2Dialect");
		System.setProperty("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		System.setProperty("spring.jpa.show-sql", "true");
		
		System.setProperty("spring.h2.console.enabled", "true");
		System.setProperty("spring.h2.console.path", "/h2-console");
		
		
		
		SpringApplication.run(TasktrackerApplication.class, args);
	}
}
