package com.bhargava.dao;

import com.bhargava.model.UserDetails;

public interface UserDAO {

	void saveUserDetails(UserDetails user);

	boolean checkValidity(UserDetails user);

}
