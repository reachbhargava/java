package org.bhargava.repository;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.bhargava.model.Userdetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBPopulator {
	@Autowired
	private UserdetailsRepository userdetailsRepository;

	// @PostConstruct
	void init() {
		try {
			Userdetails t1 = new Userdetails("bhargava", "bhargavaP");
			Userdetails t2 = new Userdetails("siri", "siriP");
			Userdetails t3 = new Userdetails("bharath", "bharathP");
			userdetailsRepository.save(Arrays.asList(t1, t2, t3));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
