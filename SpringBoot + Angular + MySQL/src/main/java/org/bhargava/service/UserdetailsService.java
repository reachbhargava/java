package org.bhargava.service;

import java.util.ArrayList;
import java.util.List;

import org.bhargava.model.Userdetails;
import org.bhargava.repository.UserdetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserdetailsService {

	@Autowired
	private UserdetailsRepository userdetailsRepository;

	public List<Userdetails> getAllUserdetails() {
		List<Userdetails> allUsers = new ArrayList<>();
		Iterable<Userdetails> result = userdetailsRepository.findAll();
		result.forEach(allUsers::add);
		return allUsers;
	}

	public Userdetails addUser(Userdetails userdetails) {
		Userdetails userdetailsInDB = userdetailsRepository.findByUsername(userdetails.getUsername());
		if (userdetailsInDB != null) {
			return null;
		}		
		return userdetailsRepository.save(userdetails);
	}

}
