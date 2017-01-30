package org.bhargava.controller;

import java.util.List;

import org.bhargava.model.Userdetails;
import org.bhargava.service.UserdetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserdetailsController {

	@Autowired
	private UserdetailsService userdetailsService;

	@GetMapping(value = "/getUserdetails")
	public List<Userdetails> getUserdetails() {
		System.out.println("comes here");
		return userdetailsService.getAllUserdetails();
	}

	@PostMapping(value = "/adduser")
	public @ResponseBody Userdetails addUser(@RequestBody Userdetails userdetails) {
		System.out.println("Coming here boss " + userdetails.getUsername());
		System.out.println("Coming here boss " + userdetails.getPassword());
		return userdetailsService.addUser(userdetails);
	}

}
