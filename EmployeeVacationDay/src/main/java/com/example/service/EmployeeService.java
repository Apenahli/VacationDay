package com.example.service;

import java.util.List;
import com.example.model.Employee;

public interface EmployeeService {

	List<Employee> findAll();
	
	List<Employee> findEmp();

	Employee findById(int id);

	void insert(Employee employee);

	void edit(Employee employee);

	void delete(int id);
	
	

}
