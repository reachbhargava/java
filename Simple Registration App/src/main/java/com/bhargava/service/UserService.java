package com.bhargava.service;

import com.bhargava.model.UserDetails;

public interface UserService {
	boolean saveUserDetails(UserDetails user);
	
	boolean checkValidity(UserDetails user);
}
