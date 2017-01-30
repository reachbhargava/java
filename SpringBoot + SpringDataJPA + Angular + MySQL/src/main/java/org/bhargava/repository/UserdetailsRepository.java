package org.bhargava.repository;

import org.bhargava.model.Userdetails;
import org.springframework.data.repository.CrudRepository;

public interface UserdetailsRepository extends CrudRepository<Userdetails, Integer> {
	
	public Userdetails findByUsername(String username);

}
