package com.sts.eventmgmt.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sts.eventmgmt.controllers.exceptions.AlreadyCheckInException;
import com.sts.eventmgmt.controllers.exceptions.AlreadyCheckOutException;
import com.sts.eventmgmt.entities.Event;
import com.sts.eventmgmt.entities.Participant;
import com.sts.eventmgmt.repos.ParticipantRepository;

@RepositoryRestController
@RequestMapping("/events")
public class CheckInController {

	@Autowired
	private ParticipantRepository participantRepository;

	@PostMapping("/checkin/{id}")
	public ResponseEntity<PersistentEntityResource> checkIn(@PathVariable Long id,
			PersistentEntityResourceAssembler assembler) {
		System.out.println(">> CheckInController, id = " + id);
		Optional<Participant> participantOptional = participantRepository.findById(id);
		Participant participant = participantOptional.get();
		
		if (participantOptional.isPresent()) {
			if (participant.getCheckedIn()) {
				throw new AlreadyCheckInException();
			} else {
				participant.setCheckedIn(true);
				participantRepository.save(participant);
			}
		} else {
			System.out.println(">> CheckInController: participant ==null");
			return null;
		}
		return ResponseEntity.ok(assembler.toFullResource(participant));
	}
	@PostMapping("/checkout/{id}")
	public ResponseEntity<PersistentEntityResource> checkOut(@PathVariable Long id,
			PersistentEntityResourceAssembler assembler) throws AlreadyCheckOutException {
		System.out.println(">> CheckInController: checkout, id = " + id);
		Optional<Participant> participantOptional = participantRepository.findById(id);
		Participant participant = participantOptional.get();
		
		if (participantOptional.isPresent()) {
			if (!participant.getCheckedIn()) {
				throw new AlreadyCheckOutException();
			} else {
				participant.setCheckedIn(false);
				participantRepository.save(participant);
			}
		} else {
			System.out.println(">> CheckInController: checkout participant ==null");
			return null;
		}
		return ResponseEntity.ok(assembler.toFullResource(participant));
	}
	
	

}
