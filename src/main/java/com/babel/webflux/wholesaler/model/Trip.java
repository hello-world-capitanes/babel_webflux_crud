package com.babel.webflux.wholesaler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("TRIP")
public class Trip implements Persistable<String>{

	@Id
	private String destination;
	private int duration;
	@Column("BASEPRICE")
	private int basePrice;

	@Transient
	private boolean isNew = false;

	public Trip(String destination, int duration, int basePrice) {
		this.destination = destination;
		this.duration = duration;
		this.basePrice = basePrice;
	}

	public Trip() {
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(int baseprice) {
		this.basePrice = baseprice;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	@Transient
	@Override
	@JsonIgnore
	public boolean isNew() {
		return isNew;
	}

	@Override
	@JsonIgnore
	public String getId() {
		return destination;
	}

	@Override
	public String toString() {
		return "Trip{" + "destination='" + destination + '\'' + ", duration=" + duration + ", basePrice=" + basePrice + '}';
	}

}