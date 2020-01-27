package com.example.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 40)
	private String name;

	@Column(length = 45)
	private String surname;

	@Column(length = 45)
	private String fatherName;

	// ise baslama tarixi
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "start_time")
	@Temporal(TemporalType.DATE)
	private Date startTime;

	@OneToMany(mappedBy = "employee")
	private List<Vacation> vacations;

	// employeenin emek staji indiye qederki(bu sirkete qederki)
	@Column(nullable = true)
	private String employeeStaj;

	// 2018-2019 qaliq mezuniyyet gunleri (kohne qaliq)
	@Column(columnDefinition = "Decimal default '0'")
	private int remainder;

	public Employee() {

	}

	public int getRemainder() {
		return remainder;
	}

	public void setRemainder(int remainder) {
		this.remainder = remainder;
	}

	public String getEmployeeStaj() {
		return employeeStaj;
	}

	public void setEmployeeStaj(String employeeStaj) {
		this.employeeStaj = employeeStaj;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public List<Vacation> getVacations() {
		return vacations;
	}

	public void setVacations(List<Vacation> vacations) {
		this.vacations = vacations;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", surname=" + surname + ", fatherName=" + fatherName
				+ ", startTime=" + startTime + "]";
	}

}
