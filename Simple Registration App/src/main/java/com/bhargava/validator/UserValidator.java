package com.bhargava.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bhargava.model.UserDetails;
import com.bhargava.service.UserService;

/** 
 * Validator that checks for custom data validity. We have a check to see if the username is already taken.
 * 
 * @author Bhargava
 *
 */
@Component
public class UserValidator implements Validator {
	@Autowired
	private UserService userService;

	@Autowired
	private MessageSource messageSource;

	@Override
	public boolean supports(Class<?> aClass) {
		return UserDetails.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserDetails userDetails = (UserDetails) target;
		if (!userService.checkValidity(userDetails)) {
			errors.rejectValue("userName", "DuplicateUserDetailsUserName",
					messageSource.getMessage("DuplicateUserDetailsUserName", null, null));
		}
	}
}
