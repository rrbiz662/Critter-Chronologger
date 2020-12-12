package com.udacity.jdnd.course3.critter.pet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.UserService;

@Service
public class PetService {

	@Autowired
	private PetRepository petRepository;
	@Autowired
	private UserService userService;
	
	public Pet savePet(Pet pet) {
		long ownerId = pet.getCustomer().getId();
		
		// Check for valid owner id
		if(ownerId > 0){
			Customer customer = userService.getCustomer(ownerId);
			
			// Check customer exists in db
			if(customer != null) {
				// Update customer's pets
				List<Pet> pets = (customer.getPets() != null ? customer.getPets() : new ArrayList<Pet>());			
				pets.add(pet);
				customer.setPets(pets);
				
				// Update pet customer
				pet.setCustomer(customer);
			}			
		}
		// Update pet in db
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
		Pet pet = petRepository.findById(petId).get();
		
		// Ck pet exists
		if(pet != null) {
			Customer customer = userService.getCustomer(pet.getCustomer().getId());
			// Ck customer exists
			if(customer != null) {
				// Update customer's pet
				List<Pet> pets = (customer.getPets() != null ? customer.getPets() : new ArrayList<Pet>());			
				pets.remove(pet);					
				customer.setPets(pets);				
			}	
			
			// Remove pet from db
			petRepository.deleteById(petId);
		}		
	}
	
}
