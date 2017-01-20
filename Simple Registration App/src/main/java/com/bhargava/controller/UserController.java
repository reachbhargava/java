package com.bhargava.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bhargava.model.UserDetails;
import com.bhargava.service.UserService;
import com.bhargava.validationresponse.ValidationResponse;
import com.bhargava.validator.UserValidator;

/**
 * Controller file that handles the navigation aspects for the flow.
 * services are autowired to the controller.
 * 
 * @author Bhargava
 *
 */
@Controller
public class UserController {

	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	@Autowired(required = true)
	@Qualifier(value = "userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(Model model) {
		model.addAttribute("userDetails", new UserDetails());
		return "welcome";
	}

	@RequestMapping(value = "/validateUserName")
	public @ResponseBody ValidationResponse processForm(Model model, @Valid UserDetails ud, BindingResult result) {
		userValidator.validate(ud, result);
		ValidationResponse res = new ValidationResponse();
		if (result.hasErrors()) {
	        res.setStatus("FAIL");
	        for (ObjectError error : result.getAllErrors()) {
	            if (error instanceof FieldError) {
	                FieldError fieldError = (FieldError) error;
	                res.addError(fieldError.getField(), fieldError.getDefaultMessage());
	            }    
	        }
	    }
	    else {
	        res.setStatus("SUCCESS");
	    }
	    return res;
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("userDetails") @Valid UserDetails ud, BindingResult result,
			RedirectAttributes redirectAttributes, Locale locale) {
		userValidator.validate(ud, result);

		if (result.hasErrors()) {
			return "welcome";
		}

		boolean registered = this.userService.saveUserDetails(ud);

		if (registered) {
			redirectAttributes.addFlashAttribute("message",
					messageSource.getMessage("success", new Object[] { ud.getUserName() }, locale));
		}
		return "redirect:/";
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest req, Exception ex) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", ex);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("error");
		return mav;
	}

}
