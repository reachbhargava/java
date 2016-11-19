package edu.service;

import java.util.HashMap;
import java.util.Map;

public class User {

	static User user = null;
	public static HashMap<String, HashMap<String, Integer>> usersCart = new HashMap<String, HashMap<String, Integer>>();

	private User() {

	}

	public static User getInstance() {
		if (user == null)
			user = new User();

		return user;
	}

}
