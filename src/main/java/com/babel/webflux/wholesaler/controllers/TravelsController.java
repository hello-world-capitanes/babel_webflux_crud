package com.babel.webflux.wholesaler.controllers;

import com.babel.webflux.wholesaler.model.Trip;
import com.babel.webflux.wholesaler.repository.TravelRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.data.domain.Persistable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/tourist-wholesaler")
public class TravelsController {

	@Autowired
	private TravelRespository tripRepository;

	@GetMapping(value="/trip", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Trip> getTrip(){
		return tripRepository.findRandomTrip();
	}

	@GetMapping(value="/trips/{destination}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<Trip>> getTrips(@PathVariable("destination") String destination){
		Mono<Trip> trip = tripRepository.findById(destination);

		return trip.map(ResponseEntity::ok)
				.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
	}

	@GetMapping(value = "/trips", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Trip> getAllTrips(){
		return tripRepository.findAll().log();
	}

	@PutMapping(value = "/trips/{destination}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<Trip>> updateTrip(@RequestBody Trip trip, @PathVariable("destination") String destination){
		trip.setDestination(destination);
		try {
			return tripRepository.save(trip).map(ResponseEntity::ok);
		}catch (TransientDataAccessException e){
			return Mono.just(ResponseEntity.notFound().build());
		}
	}

	@PostMapping(value = "/trips/{destination}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Trip> createTrip(@RequestBody Trip trip){
		trip.setNew(true);
		return tripRepository.save(trip);
	}



}
