package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private Enum<DayOfWeek> schedule;

	public Long getId() {
		return id;
	}

	public void setId(Long userId) {
		this.id = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Enum<DayOfWeek> getSchedule() {
		return schedule;
	}

	public void setSchedule(Enum<DayOfWeek> schedule) {
		this.schedule = schedule;
	}	
	
}
