package com.example.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@Transient // deyer tutur ama qaliciliq baximindan bir onemi olmur yeni dbda gorsenmir
	private String description=" ";

	private LocalDateTime start;

	@Transient // deyer tutur ama qaliciliq baximindan bir onemi olmur yeni dbda gorsenmir
	private LocalDateTime finish=LocalDateTime.now();

	public Event(Long id, String title, String description, LocalDateTime start, LocalDateTime finish) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.start = start;
		this.finish = finish;
	}

	public Event() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getFinish() {
		return finish;
	}

	public void setFinish(LocalDateTime finish) {
		this.finish = finish;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", title=" + title + ", start=" + start + "]";
	}



}
