package com.udacity.jdnd.course3.critter.user;

import javax.persistence.Entity;

@Entity
public class Employee extends User{
	private String phoneNumber;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
