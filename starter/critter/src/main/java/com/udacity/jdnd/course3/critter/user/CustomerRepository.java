package com.udacity.jdnd.course3.critter.user;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	public Customer findCustomerByPetsId(long petId);
}
