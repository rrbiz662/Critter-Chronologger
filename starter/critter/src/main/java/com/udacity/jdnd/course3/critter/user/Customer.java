package com.udacity.jdnd.course3.critter.user;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.udacity.jdnd.course3.critter.pet.Pet;

@Entity
public class Customer extends User {
	private Enum<EmployeeSkill> skills;
	@OneToMany
	private List<Pet> pets;

	public Enum<EmployeeSkill> getSkills() {
		return skills;
	}

	public void setSkills(Enum<EmployeeSkill>  skills) {
		this.skills = skills;
	}	
	
	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}
	
}
