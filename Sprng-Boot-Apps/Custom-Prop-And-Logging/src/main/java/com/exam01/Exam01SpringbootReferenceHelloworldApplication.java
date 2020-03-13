package com.exam01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableAutoConfiguration
@ComponentScan("com.exam01")
public class Exam01SpringbootReferenceHelloworldApplication implements CommandLineRunner {

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}
	
	@Autowired
    ApplicationContext context;
	
	@Autowired
	MyService service;
	
	@Autowired
	MyBean myBean;
	
	public static void main(String[] args) {
		SpringApplication  app = new SpringApplication(Exam01SpringbootReferenceHelloworldApplication.class);
		app.run(args);
		
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Exam01SpringbootReferenceHelloworldApplication.run()");
		List<String> beanNames = getAllBean(context);
		System.out.println("beanNames :"+ beanNames);
		System.out.println(myBean.name);
		service.readPropertiesValue();
	}
	
	 List<String> getAllBean(ApplicationContext context) {
		String[] allBean = context.getBeanDefinitionNames();
		Arrays.sort(allBean);
		List<String> beanNames = new ArrayList<>();
		for(String bean: allBean)	{
			beanNames.add(bean);
		//	System.out.println(bean);
		}
	//	System.out.println("beanNames :"+ beanNames);
		return beanNames;
	}
	
	static void start() {
		System.out.println("Exam01SpringbootReferenceHelloworldApplication.start()");
	}
	
	static void end() {
		System.out.println("Exam01SpringbootReferenceHelloworldApplication.end()");
	}
	
	static void exit(String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(Exam01SpringbootReferenceHelloworldApplication.class, args)));
	}
	
	@Bean
	public ExitCodeGenerator exitCodeGenerator() {
		System.out.println("Exam01SpringbootReferenceHelloworldApplication.exitCodeGenerator()");
		return () -> 42;
	}
}