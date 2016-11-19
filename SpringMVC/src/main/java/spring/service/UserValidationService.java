package spring.service;

import org.springframework.stereotype.Service;

@Service
public class UserValidationService {
	public boolean checkValidity(String name, String password) {
		boolean isValid = false;
		if ("siri".equalsIgnoreCase(name) && "shree".equalsIgnoreCase(password)) {
			isValid = true;
		}
		return isValid;
	}
}
