package com.exam01;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

	@Value("${name}")
	String name;
	
	
}
