package com.babel.webflux.wholesaler.repository;

import com.babel.webflux.wholesaler.model.Trip;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TravelRespository extends ReactiveCrudRepository<Trip, String>{
	@Query("SELECT destination, duration, baseprice FROM trip ORDER BY dbms_random.value() FETCH FIRST ROW ONLY")
	Mono<Trip> findRandomTrip();

}
