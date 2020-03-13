package com.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.Employee;
import com.utils.CsvUtils;

@Controller
public class DownloadController {
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	// Request Header : http://localhost:8080/download/employee
	//@GetMapping("/download/employee.csv")
	@GetMapping("/download/employee")
	public void downloadCsv(HttpServletResponse response) throws IOException	{
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; file=employee.csv");
		CsvUtils.downloadCsv(response.getWriter(), createTestData());
	}
	
	private List<Employee> createTestData() {
		List<Employee> data = new ArrayList<>();
		data.add(new Employee(123, "Buffet","Jimmy"));
        data.add(new Employee(456, "Cartman","Eric"));
        data.add(new Employee(789, "Jefferson","George"));
        return data;
		
		
	}
}
