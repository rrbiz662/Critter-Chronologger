package com.udacity.jdnd.course3.critter.schedule;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

@Entity
public class Schedule {
	@Id
	@GeneratedValue
	private long id;
	@ManyToMany
	private List<Employee> employees;
	@ManyToMany
	private List<Pet> pets;
	private LocalDate date;
	@ElementCollection
	private Set<EmployeeSkill> skills;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	public List<Pet> getPets() {
		return pets;
	}
	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Set<EmployeeSkill> getSkills() {
		return skills;
	}
	public void setSkills(Set<EmployeeSkill> skills) {
		this.skills = skills;
	}	
}
