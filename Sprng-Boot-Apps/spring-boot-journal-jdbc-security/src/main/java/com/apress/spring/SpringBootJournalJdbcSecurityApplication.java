package com.apress.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootJournalJdbcSecurityApplication {

	public static void main(String[] args) {
		System.out.println("main() starts");
		SpringApplication.run(SpringBootJournalJdbcSecurityApplication.class, args);
		System.out.println("main() end");
	}
}
