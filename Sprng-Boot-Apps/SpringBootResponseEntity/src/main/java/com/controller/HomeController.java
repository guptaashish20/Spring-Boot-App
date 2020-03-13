package com.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	/*	http://localhost:8080/hello	*/
	@GetMapping("/hello")
	ResponseEntity<String> hello() {
		return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
	}

	/*	http://localhost:8080/age?yearOfBirth=2089 */
	@GetMapping("/age")
	ResponseEntity<String> age(@RequestParam("yearOfBirth") int yearOfBirth) {

		System.out.println("yearOfBirth :" + yearOfBirth);
		if (yearOfBirth > 2020) {
			return new ResponseEntity<>("Year of birth cannot be in the future", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("Your age is " + yearOfBirth, HttpStatus.OK);
	}
	
	/*	http://localhost:8080/customHeader	*/
	@GetMapping("/customHeader")
	ResponseEntity<String> customHeader() {
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Custom-Header", "foo");
	         
	    return new ResponseEntity<>(
	      "Custom header set", headers, HttpStatus.OK);
	}
}
