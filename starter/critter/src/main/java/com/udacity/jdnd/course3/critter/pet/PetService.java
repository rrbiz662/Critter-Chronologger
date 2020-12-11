package com.udacity.jdnd.course3.critter.pet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

	@Autowired
	private PetRepository petRepository;
	
	public Pet savePet(Pet pet) {
		return petRepository.save(pet);
	}
	
	public Pet getPet(long petId) {
		return petRepository.findById(petId).get();
	}
	
	public Iterable<Pet> getPets(){
		return petRepository.findAll();
	}
	
	public List<Pet> getPetsByOwner(long customerId){
		return petRepository.findPetsByCustomerId(customerId);		
	}
	
	public void deletePet(long petId) {
		petRepository.deleteById(petId);
	}
	
}
