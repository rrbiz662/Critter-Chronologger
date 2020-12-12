package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
	
	@Autowired
	private ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return mapScheduleToDto(scheduleService.saveSchedule(mapDtoToSchedule(scheduleDTO)));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
    	List<ScheduleDTO> dtos = new ArrayList<ScheduleDTO>();
    	
    	for (Schedule schedule : scheduleService.getSchedules()) {
			dtos.add(mapScheduleToDto(schedule));
		}   	
    	
        return dtos;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<ScheduleDTO> dtos = new ArrayList<ScheduleDTO>();
        
        for (Schedule schedule : scheduleService.getSchedulesForPet(petId)) {
			dtos.add(mapScheduleToDto(schedule));
		}
        
        return dtos;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<ScheduleDTO> dtos = new ArrayList<ScheduleDTO>();
        
        for (Schedule schedule : scheduleService.getSchedulesForEmployee(employeeId)) {
			dtos.add(mapScheduleToDto(schedule));
		}
        
        return dtos;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<ScheduleDTO> dtos = new ArrayList<ScheduleDTO>();
        
        for (Schedule schedule : scheduleService.getSchedulesForCustomer(customerId)) {
			dtos.add(mapScheduleToDto(schedule));
		}
        
        return dtos;
    }
    
    private Schedule mapDtoToSchedule(ScheduleDTO dto) {
    	Schedule schedule = new Schedule();
    	BeanUtils.copyProperties(dto, schedule);
    	List<Employee> employees = new ArrayList<Employee>();
    	List<Pet> pets = new ArrayList<Pet>();
    	
    	schedule.setSkills(dto.getActivities());
    	
    	if(dto.getEmployeeIds() != null) {
        	for (long id : dto.getEmployeeIds()) {
        		Employee tempEmployee = new Employee();
        		tempEmployee.setId(id);
    			employees.add(tempEmployee);
    		}
        	schedule.setEmployees(employees);
    	}

    	if(dto.getPetIds() != null) {
        	for (long id : dto.getPetIds()) {
        		Pet tempPet = new Pet();
        		tempPet.setId(id);
    			pets.add(tempPet);
    		}
        	schedule.setPets(pets);
    	}	
    	
    	return schedule;
    }
    
    private ScheduleDTO mapScheduleToDto(Schedule schedule) {
    	ScheduleDTO dto = new ScheduleDTO();
    	BeanUtils.copyProperties(schedule, dto);
    	List<Long> employeeIds = new ArrayList<Long>();
    	List<Long> petIds = new ArrayList<Long>();
    	
    	dto.setActivities(schedule.getSkills());
    	
    	if(schedule.getEmployees() != null) {
        	for (Employee employee : schedule.getEmployees()) {
    			employeeIds.add(employee.getId());
    		}
        	dto.setEmployeeIds(employeeIds);
    	}
    	
    	if(schedule.getPets() != null) {
        	for (Pet pet : schedule.getPets()) {
    			petIds.add(pet.getId());
    		}
        	dto.setPetIds(petIds);
    	}
    	
    	return dto;
    }
}
