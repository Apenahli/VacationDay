package com.example.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Event;
import com.example.repository.EventRepository;
import com.example.service.EventService;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;

	@Override
	public List<Event> findAll() {

		return (List<Event>) eventRepository.findAll();
	}

	@Override
	public Event save(Event event) {

		return eventRepository.save(event);
	}

	@Override
	public void remove(Event event) {
		eventRepository.delete(event);

	}

	@Override
	public Event edit(Event event) {
		return eventRepository.save(event);
	}

}
