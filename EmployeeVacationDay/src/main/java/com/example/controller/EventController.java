package com.example.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.model.Event;
import com.example.service.EventService;

@RestController
public class EventController {

	@Autowired
	private EventService eventService;

	@RequestMapping(value = "/allevents", method = RequestMethod.GET)
	public List<Event> allEvents() {

		// System.out.println("**************** allEvents :----> allEvents");

		return eventService.findAll();
	}

	@RequestMapping(value = "/event", method = RequestMethod.POST)
	public Event addEvent(@RequestBody Event event) {

		// System.out.println("**************** addEvent :----> addEvent");

		LocalDateTime localDate1 = event.getStart();
		localDate1 = localDate1.plusDays(1);

		LocalDateTime localDate2 = event.getFinish();
		localDate2 = localDate2.plusDays(1);

		event.setStart(localDate1);
		event.setFinish(localDate2);

		Event created = eventService.save(event);

		return created;
	}

	@RequestMapping(value = "/event", method = RequestMethod.DELETE)
	public void removeEvent(@RequestBody Event event) {

		// System.out.println(event);

		// System.out.println("**************** removeEvent :----> removeEvent");

		eventService.remove(event);
	}

}
