package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.VacationMonths;
import com.example.repository.VacationMonthsRepository;
import com.example.service.VacationMonthsService;

@Service
public class VacationMonthsServiceImpl implements VacationMonthsService {

	@Autowired
	private VacationMonthsRepository monthsRepository;

	@Override
	public List<VacationMonths> findAll() {

		return (List<VacationMonths>) monthsRepository.findAll();
	}

	@Override
	public VacationMonths findById(int id) {

		return monthsRepository.findById(id).get();
	}

	@Override
	public void insert(VacationMonths months) {
		monthsRepository.save(months);

	}

	@Override
	public void edit(VacationMonths months) {
		monthsRepository.save(months);

	}

	@Override
	public void delete(int id) {

		monthsRepository.deleteById(id);
	}

	@Override
	public List<VacationMonths> findVacationMonths(int id) {

		return monthsRepository.findVacationMonths(id);
	}

}
