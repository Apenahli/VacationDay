package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Vacation;
import com.example.repository.VacationRepository;
import com.example.service.VacationService;

@Service
public class VacationServiceImpl implements VacationService {
	@Autowired
	private VacationRepository vacationRepository;

	@Override
	public List<Vacation> findALl() {

		return (List<Vacation>) vacationRepository.findAll();
	}

	@Override
	public Vacation findById(int id) {
		return vacationRepository.findById(id).get();
	}

	@Override
	public Vacation insert(Vacation vacation) {
		return vacationRepository.save(vacation);

	}

	@Override
	public void edit(Vacation vacation) {
		vacationRepository.save(vacation);

	}

	@Override
	public void delete(int id) {
		vacationRepository.deleteById(id);

	}

	@Override
	public List<Vacation> findVacatioByYear(String year) {

		return vacationRepository.findVacatioByYear(year);
	}

	@Override
	public void updateRemainder(int remainderId, int id) {
		vacationRepository.updateRemainder(remainderId, id);

	}

	@Override
	public Vacation getOldRemander(String year, int emp_id) {

		return vacationRepository.getOldRemander(year, emp_id);
	}

	/*
	 * @Override public Vacation findOldYearVacation(String year, int id) {
	 * 
	 * return vacationRepository.findOldYearVacation(year, id); }
	 */

}
