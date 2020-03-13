package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	// http://localhost:8080
	@GetMapping("/")
	public String welcome() {
		return "welcome";
	}
	
	// localhost:8080/index
	@GetMapping("/index")
	public String index() {
		return "index";
	}
}
