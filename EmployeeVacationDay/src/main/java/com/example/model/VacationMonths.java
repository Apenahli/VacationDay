package com.example.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class VacationMonths implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// baslama tarixi
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "start_time")
	@Temporal(TemporalType.DATE)
	private Date startTime;

	// bitme tarixi
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "end_time")
	@Temporal(TemporalType.DATE)
	private Date endTime;

	@ManyToOne
	@JoinColumn(name = "vacation_id")
	private Vacation vacation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Vacation getVacation() {
		return vacation;
	}

	public void setVacation(Vacation vacation) {
		this.vacation = vacation;
	}

	@Override
	public String toString() {
		return "VacationMonths [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", vacation="
				+ vacation + "]";
	}

}
