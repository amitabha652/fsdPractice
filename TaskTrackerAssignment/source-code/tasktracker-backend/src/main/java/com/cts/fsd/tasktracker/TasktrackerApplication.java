package com.cts.fsd.tasktracker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cts.fsd.tasktracker.test.TestClass;

@SpringBootApplication
public class TasktrackerApplication {
	public static void main(String[] args) throws IOException {

		
		System.setProperty("server.servlet.context-path", "/tasktrackerbackend");
//		System.setProperty("server.port", "8080");
//		final File checkFile = new File("\\tmp\\tasktracker");
//		System.out.println("checkFile.exists() =  " + checkFile.exists());
//		System.out.println("Check file path = " + new File(".\\src\\main\\resources\\DB\\tasktracker.mv.db").getAbsolutePath());
		
//		final File f = new File(TasktrackerApplication.class.getProtectionDomain().getCodeSource().getLocation().getPath());
//		String absPath = f.getAbsolutePath();
//		System.out.println("absPath ===========    " + absPath );
//		String finalDbPath = "jdbc:h2:file:" + absPath + "\\DB\\tasktracker";
//		String finalDbPath = "jdbc:h2:file:.\\tasktracker";
//		String finalDbPath = "jdbc:h2:file:.\\src\\main\\resources\\DB\\tasktracker;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";
		
//		String content = "Hello World !!";
//		Files.write(Paths.get("com/cts/fsd/tasktracker/test/Test.txt"), content.getBytes());
//		String finalDbPath = System.getProperty("spring.datasource.url");
		
		
		String finalDbPath = "jdbc:h2:file:\\tmp\\tasktracker";
		System.out.println("finalDbPath " + finalDbPath);
		System.setProperty("spring.datasource.url", finalDbPath);
		
		System.setProperty("spring.datasource.name", "task_db");
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
