package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	public List<Employee> findEmployeesBySkillsInAndDaysAvailable(Set<EmployeeSkill> skills, DayOfWeek date);
}
