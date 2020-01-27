package com.example.service;

import java.util.List;
import com.example.model.Event;

public interface EventService {

	List<Event> findAll();

	Event save(Event event);

	void remove(Event event);

	Event edit(Event event);

}