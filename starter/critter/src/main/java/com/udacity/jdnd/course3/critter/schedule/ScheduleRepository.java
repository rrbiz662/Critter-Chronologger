package com.udacity.jdnd.course3.critter.schedule;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
	
	List<Schedule> findSchedulesByPetsId(long petId);
	List<Schedule> findSchedulesByEmployeesId(long employeeId);
	List<Schedule> findSchedulesByPetsCustomerId(long customerId);
}
