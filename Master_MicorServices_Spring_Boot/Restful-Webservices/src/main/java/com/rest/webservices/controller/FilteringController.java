package com.rest.webservices.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.rest.webservices.model.*;

@RestController
public class FilteringController {

	/*
	 * Request Header : http://localhost:8080/filtering
	 * Response : 
		{
			"field1": "value1"
		}
	*/
	@GetMapping("/filtering")
	public SomeBean retrieveSomeBean() {
		return new SomeBean("value1", "value2", "value3");
	}
	
	/*
	 * Request Header : http://localhost:8080/filtering-list
	 * Response : 
	 	[
		    {
		        "field1": "value1"
		    },
		    {
		        "field1": "value12"
		    }
		]
	*/
	@GetMapping("/filtering-list")
	public List<SomeBean> retrieveListOfSomeBean() {
		return Arrays.asList(new SomeBean("value1", "value2", "value3"), 
				new SomeBean("value12", "value22", "value32"));
	}
	
	/*
	 * Request Header : http://localhost:8080/filtering2
	 * Response : 
	 * {
		    "field1": "value1",
		    "field2": "value2"
	   }
	*/
	@GetMapping("/filtering2")
	public MappingJacksonValue retrieveSomeBean2() {
		SomeBean2 someBean = new SomeBean2("value1", "value2", "value3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		return mapping;
	}
	
	/*
	 * Request Header : http://localhost:8080/filtering-list2
	 * Response : 
	 * [
		    {
		        "field2": "value2",
		        "field3": "value3"
		    },
		    {
		        "field2": "value22",
		        "field3": "value32"
		    }
		]
	*/
	@GetMapping("/filtering-list2")
	public MappingJacksonValue retrieveListOfSomeBean2() {
		List<SomeBean2> list = Arrays.asList(new SomeBean2("value1", "value2", "value3"), 
				new SomeBean2("value12", "value22", "value32"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		return mapping;
	}
	
	
}
