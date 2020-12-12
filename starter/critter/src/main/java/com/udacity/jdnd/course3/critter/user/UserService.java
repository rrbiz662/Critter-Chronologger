package com.udacity.jdnd.course3.critter.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired 
	private EmployeeRepository employeeRepository;
	@Autowired
	private CustomerRepository customerRepository;	
	
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public Customer getCustomer(Long customerId) {
		return customerRepository.findById(customerId).get();
	}
	
	public Iterable<Customer> getCustomers(){
		return customerRepository.findAll();
	}
	
	public Customer getCustomerByPet(long petId) {
		return customerRepository.findCustomerByPetsId(petId);
	}	
	
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public Employee getEmployee(long employeeId) {
		return employeeRepository.findById(employeeId).get();
	}
	
	public List<Employee> getAvailableEmployeesBySkills(Set<EmployeeSkill> skills, LocalDate date){
		List<Employee> foundEmployees = employeeRepository.findEmployeesBySkillsInAndDaysAvailable(skills, date.getDayOfWeek());
		List<Employee> exactSkillMatch = new ArrayList<Employee>();
		
		for (Employee employee : foundEmployees) {
			if (employee.getSkills().containsAll(skills)) {
				exactSkillMatch.add(employee);
			}
		}
		
		return exactSkillMatch;
	}

}
