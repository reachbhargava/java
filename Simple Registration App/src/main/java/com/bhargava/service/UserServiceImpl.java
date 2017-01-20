package com.bhargava.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bhargava.dao.UserDAO;
import com.bhargava.model.UserDetails;

/** 
 * Service layer between the DAOImpl and the Controller. This is a mere pass through in this case for standards.
 * @author Bhargava
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	@Transactional
	public boolean saveUserDetails(UserDetails user) {
		boolean registered = false;
		userDAO.saveUserDetails(user);
		registered = true;
		return registered;
	}

	@Override
	public boolean checkValidity(UserDetails user) {
		return userDAO.checkValidity(user);
	}

}
