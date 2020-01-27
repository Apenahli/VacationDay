package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	@Query(value = "SELECT id,name,surname,father_name FROM employee", nativeQuery = true)
	List<Employee> findEmp();
	
//	@Query(value = "SELECT * FROM vacations.vacation where year='2019-2020'", nativeQuery = true)
//	List<Employee> findAllFor19();

}
