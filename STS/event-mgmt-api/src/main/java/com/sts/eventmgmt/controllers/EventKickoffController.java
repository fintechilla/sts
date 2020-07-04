package com.sts.eventmgmt.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sts.eventmgmt.entities.Event;
import com.sts.eventmgmt.repos.EventRepository;

@RepositoryRestController
@RequestMapping("/events")
public class EventKickoffController {
	
	@Autowired
	private EventRepository eventRepository;
	
	@PostMapping("/start/{id}")
	public ResponseEntity start(@PathVariable Long id) {
		Optional<Event> eventOptional = eventRepository.findById(id);
		Event event;
		if(eventOptional.isPresent()) {
			event = eventOptional.get();
			event.setStarted(true);
			eventRepository.save(event);
		}else {
			throw new ResourceNotFoundException();
		}
		
		return ResponseEntity.ok(event.getName() + " has started");
	}
}
