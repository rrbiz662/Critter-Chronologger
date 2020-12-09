package com.udacity.jdnd.course3.critter.pet;

import java.time.DayOfWeek;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.udacity.jdnd.course3.critter.user.Customer;

@Entity
public class Pet {
	@Id
	@GeneratedValue
	private long id;
	private Enum<PetType> type;
	private String name;
	private LocalDate birthDate;
	private String notes;
	private Enum<DayOfWeek> schedule;
	
	@OneToOne
	private Customer owner;		

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Enum<PetType> getType() {
		return type;
	}
	public void setType(Enum<PetType> type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		birthDate.getDayOfWeek().toString();
		
		this.birthDate = birthDate;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Customer getOwner() {
		return owner;
	}
	public void setOwner(Customer owner) {
		this.owner = owner;
	}
	public Enum<DayOfWeek> getSchedule() {
		return schedule;
	}
	public void setSchedule(Enum<DayOfWeek> schedule) {
		this.schedule = schedule;
	}	
}
