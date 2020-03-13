package com.rest.webservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.webservices.model.Name;
import com.rest.webservices.model.PersonV1;
import com.rest.webservices.model.PersonV2;

@RestController
public class PersonVersionController {

	/* Request : http://localhost:8080/v2/person
	   Response : 
		 {
	    	"name": "Bob Charlie"
		 }
	 */
	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Bob Charlie");
	}
	
	/*	Request : http://localhost:8080/v2/person
	  Response : 
		 {
	    	"name": {
	        	"firstName": "Bob",
	        	"lastName": "Charlie"
	    	}
		 }
	 */
	@GetMapping("v2/person")
	public PersonV2 personV2()	{
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	/* Request : http://localhost:8080/person/param?version=1
	 * Response : 
	 	{
		    "name": "Bob Charlie"
		}
	*/
	@GetMapping(value="/person/param", params="version=1")
	public PersonV1 paramV1()	{
		return new PersonV1("Bob Charlie");
	}
	
	/* Request : http://localhost:8080/person/param?version=2
	 * Response : 
	 	{
	    	"name": {
	        	"firstName": "Bob",
	        	"lastName": "Charlie"
    		}
		}
	*/
	@GetMapping(value="/person/param", params="version=2")
	public PersonV2 paramV2()	{
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	// Using Header 
	
	/* Request : http://localhost:8080/person/header
	 * Headers : Key = X-API-VERSION, Value = 1
	 * Response : 
	 	{
		    "name": "Bob Charlie"
		}
	*/
	@GetMapping(value="/person/header", headers="X-API-VERSION=1")
	public PersonV1 headerV1()	{
		return new PersonV1("Bob Charlie");
	}
	
	/*Request : http://localhost:8080/person/header
	 * Headers : Key = X-API-VERSION, Value = 2
	 * Response : 
	 	{
	    	"name": {
	        	"firstName": "Bob",
	        	"lastName": "Charlie"
    		}
		}
	*/
	@GetMapping(value="/person/header", headers="X-API-VERSION=2")
	public PersonV2 headerV2()	{
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	// Using Produces 
	
		/* Request : http://localhost:8080/person/produces
		 * Headers : Key = Accept, Value = application/vnd.company.app-v1+json
		 * Response : 
		 	{
			    "name": "Bob Charlie"
			}
		*/
		@GetMapping(value="/person/produces", produces="application/vnd.company.app-v1+json")
		public PersonV1 producesV1()	{
			return new PersonV1("Bob Charlie");
		}
		
		/*Request : http://localhost:8080/person/produces
		 * Headers : Key = Accept, Value = application/vnd.company.app-v2+json
		 * Response : 
		 	{
		    	"name": {
		        	"firstName": "Bob",
		        	"lastName": "Charlie"
	    		}
			}
		*/
		@GetMapping(value="/person/produces", produces="application/vnd.company.app-v2+json")
		public PersonV2 producesV2()	{
			return new PersonV2(new Name("Bob", "Charlie"));
		}
	
	
}
