package com.udacity.jdnd.course3.critter.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.udacity.jdnd.course3.critter.pet.Pet;

@Entity
public class Customer extends User {
	private String phoneNumber;
	private String notes;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Pet> pets;
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public List<Pet> getPets() {
		return pets;
	}
	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}
	
}
