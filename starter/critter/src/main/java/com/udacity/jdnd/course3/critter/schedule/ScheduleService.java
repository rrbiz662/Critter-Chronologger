package com.udacity.jdnd.course3.critter.schedule;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.UserService;

@Service
public class ScheduleService {
	@Autowired
	private ScheduleRepository scheduleRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private PetService petService;
	
	public Schedule saveSchedule(Schedule schedule) {
		
		for (Employee employee : schedule.getEmployees()) {
			Employee dbEmployee = userService.getEmployee(employee.getId());
			Set<DayOfWeek> daysAvail = null;
			
			if(dbEmployee != null && dbEmployee.getDaysAvailable() != null) {
				daysAvail = dbEmployee.getDaysAvailable();				
			}
			else if (dbEmployee != null) {
				daysAvail = Sets.newHashSet();			
			}			

			daysAvail.add(schedule.getDate().getDayOfWeek());
			dbEmployee.setDaysAvailable(daysAvail);	
		}
		
		return scheduleRepository.save(schedule);
	}
	
	public Schedule getSchedule(long id) {
		return scheduleRepository.findById(id).get();
	}
	
	public Iterable<Schedule> getSchedules(){
		return scheduleRepository.findAll();
	}
	
	public List<Schedule> getSchedulesForPet(long petId){
		return scheduleRepository.findSchedulesByPetsId(petId);
	}
	
	public List<Schedule> getSchedulesForEmployee(long employeeId){
		return scheduleRepository.findSchedulesByEmployeesId(employeeId);
	}
	
	public List<Schedule> getSchedulesForCustomer(long customerId){
		return scheduleRepository.findSchedulesByPetsCustomerId(customerId);
	}
	
	
}
