package com.example.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.model.Vacation;

public interface VacationService {

	List<Vacation> findALl();

	List<Vacation> findVacatioByYear(String year);

	/* Vacation findOldYearVacation(String year,int id); */

	Vacation findById(int id);

	Vacation insert(Vacation vacation);

	void edit(Vacation vacation);

	void delete(int id);

	void updateRemainder(int remainderId, int id);

	Vacation getOldRemander(String year, int emp_id);

}
