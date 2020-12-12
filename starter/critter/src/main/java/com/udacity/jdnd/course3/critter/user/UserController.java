package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private PetService petService;
	@Autowired
	private UserService userService;	

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return mapCustomerToDto(userService.saveCustomer(mapDtoToCustomer(customerDTO)));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<CustomerDTO> customerDTOs = new ArrayList<CustomerDTO>();
        
        for(Customer customer : userService.getCustomers()) {
			customerDTOs.add(mapCustomerToDto(customer));
		}
        
        return customerDTOs;
    }
    
    @GetMapping("/customer/{customerId}")
    public CustomerDTO getOwner(@PathVariable long customerId) {
    	return mapCustomerToDto(userService.getCustomer(customerId));
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        return mapCustomerToDto(userService.getCustomerByPet(petId));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return mapEmployeeToDto(userService.saveEmployee(mapDtoToEmployee(employeeDTO)));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return mapEmployeeToDto(userService.getEmployee(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        Employee employee = userService.getEmployee(employeeId);
        
        employee.setDaysAvailable(daysAvailable);
        
        userService.saveEmployee(employee);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<EmployeeDTO> dtos = new ArrayList<EmployeeDTO>();
        
        for (Employee employee : userService.getAvailableEmployeesBySkills(employeeDTO.getSkills(), employeeDTO.getDate())) {			
        	dtos.add(mapEmployeeToDto(employee));
		}
        
    	return dtos;
    }
    
    private CustomerDTO mapCustomerToDto(Customer customer) {    	
    	CustomerDTO dto = new CustomerDTO();
    	BeanUtils.copyProperties(customer, dto);
    	
    	if(customer.getPets() != null) {
    		List<Long> petIds = new ArrayList<Long>();
    	
    		// Copy pet IDs.
    		for (Pet pet : customer.getPets()) {
    			petIds.add(pet.getId());
    		}
    	
    		dto.setPetIds(petIds);   
    	}
    	
    	return dto;
    }
    
    private Customer mapDtoToCustomer(CustomerDTO dto) {    	
    	Customer customer = new Customer();
    	BeanUtils.copyProperties(dto, customer);
    	
       	if(dto.getPetIds() != null) {
    		List<Pet> pets = new ArrayList<Pet>();
    		
        	for (Long petId : dto.getPetIds()) {
    			pets.add(petService.getPet(petId));
    		}
        	
        	customer.setPets(pets);
    	}
    	
    	return customer;
    }
    
    private Employee mapDtoToEmployee(EmployeeDTO dto) {
    	Employee employee = new Employee();
    	BeanUtils.copyProperties(dto, employee);
    	
    	return employee;
    }
    
    private EmployeeDTO mapEmployeeToDto(Employee employee) {
    	EmployeeDTO dto = new EmployeeDTO();
    	BeanUtils.copyProperties(employee, dto);
    	
    	return dto;
    }

}
