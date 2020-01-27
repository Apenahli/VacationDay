package com.example.service;

import java.util.List;
import com.example.model.VacationMonths;

public interface VacationMonthsService {

	List<VacationMonths> findAll();

	List<VacationMonths> findVacationMonths(int id);

	VacationMonths findById(int id);

	void insert(VacationMonths months);

	void edit(VacationMonths months);

	void delete(int id);
}
