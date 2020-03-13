package com.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.bean.LimitConfiguration;
import com.microservices.config.Configuration;

@RestController
public class LimitConfigurationController {

	@Autowired
	private Configuration configuration;
	
	// Request : http://localhost:8080/limits
	// Response : {   "maximum": 1000, "minimum": 1 }
	@GetMapping("/limits")
	public LimitConfiguration retrieveLimitsFromConfigurations() {
		System.out.println(configuration.getMaximum());
		System.out.println(configuration.getMinimum());
		return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
	}
}
