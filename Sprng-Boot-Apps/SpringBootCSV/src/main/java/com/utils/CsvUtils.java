package com.utils;

import java.io.PrintWriter;
import java.util.List;

import com.model.Employee;

public class CsvUtils {
	
	public static void downloadCsv(PrintWriter writer, List<Employee> employees)	{
		writer.write("Employee ID, FirstName, Last Name\n");
		for(Employee employee: employees)	{
			writer.write(employee.getEmployeeId() + "," + employee.getFirstName() + "," + employee.getLastName() + "\n");
		}
	}
}
