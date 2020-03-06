package com.ibaseit.ecommerceapplicationspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibaseit.ecommerceapplicationspring.entity.User;
import com.ibaseit.ecommerceapplicationspring.serviceimpl.UserServiceImpl;

@RestController
@RequestMapping(path = "/user")

public class UserController {
	@Autowired
	private UserServiceImpl userService;

	@GetMapping(path = "/userdetails", produces = "application/json")
	public User getUserDetails(@RequestParam("userId") long userId) throws Exception {
		return userService.getUserDetails(userId);
	}

	@PostMapping(path = "/saveUser", consumes = "application/json", produces = "application/json")
	public String saveUser(@RequestBody User user) throws Exception {
		userService.saveUser(user);
		return "succes";
	}

	@PutMapping(path = "/updateUser", produces = "application/json")
	public User updateUser(@RequestBody User user) throws Exception {
		return userService.updateUser(user);
	}

}