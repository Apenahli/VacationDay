package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import com.example.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> findAll() {

		return (List<Employee>) employeeRepository.findAll();
	}

	@Override
	public Employee findById(int id) {

		return employeeRepository.findById(id).get();
	}

	@Override
	public void insert(Employee employee) {
		employeeRepository.save(employee);

	}

	@Override
	public void edit(Employee employee) {
		employeeRepository.save(employee);

	}

	@Override
	public void delete(int id) {
		employeeRepository.deleteById(id);

	}

	@Override
	public List<Employee> findEmp() {

		return employeeRepository.findEmp();
	}

}
