package com.example.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Vacation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// 2019-2010
	@Column(length = 40)
	private String year;

	// esas emek meuniyyet gunleri
	private int basic_vacation_day;

	// sosial mezuniyyet(usqli qadinlara)
	private int sosial_vacation_day;

	// emek stajina gore elave mezuniyyet
	@Transient // deyer tutur ama qaliciliq baximindan bir onemi olmur yeni dbda gorsenmir
	private int stajVacationDay;

	// son netice,hesab
	@Transient // deyer tutur ama qaliciliq baximindan bir onemi olmur yeni dbda gorsenmir
	private int total;

	// 2019-2020 qaliq mezuniyyet gunleri
	@Transient // deyer tutur ama qaliciliq baximindan bir onemi olmur yeni dbda gorsenmir
	private int final_remainder;

	@ManyToOne
	@JoinColumn(name = "emp_id")
	private Employee employee;

	@OneToMany(mappedBy = "vacation")
	private List<VacationMonths> months;

	// 2018-2019 qaliq mezuniyyet gunleri (kohne qaliq)
	@Column(columnDefinition = "Decimal default '0'")
	private int remainder;

	public int getRemainder() {
		return remainder;
	}

	public void setRemainder(int remainder) {
		this.remainder = remainder;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getBasic_vacation_day() {
		return basic_vacation_day;
	}

	public void setBasic_vacation_day(int basic_vacation_day) {
		this.basic_vacation_day = basic_vacation_day;
	}

	public int getSosial_vacation_day() {
		return sosial_vacation_day;
	}

	public void setSosial_vacation_day(int sosial_vacation_day) {
		this.sosial_vacation_day = sosial_vacation_day;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getFinal_remainder() {
		return final_remainder;
	}

	public void setFinal_remainder(int final_remainder) {
		this.final_remainder = final_remainder;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<VacationMonths> getMonths() {
		return months;
	}

	public void setMonths(List<VacationMonths> months) {
		this.months = months;
	}

	public int getStajVacationDay() {
		return stajVacationDay;
	}

	public void setStajVacationDay(int stajVacationDay) {
		this.stajVacationDay = stajVacationDay;
	}

	@Override
	public String toString() {
		return "Vacation [id=" + id + ", year=" + year + ", basic_vacation_day=" + basic_vacation_day
				+ ", sosial_vacation_day=" + sosial_vacation_day + ", stajVacationDay=" + stajVacationDay + ", total="
				+ total + ", final_remainder=" + final_remainder + "]";
	}

}
