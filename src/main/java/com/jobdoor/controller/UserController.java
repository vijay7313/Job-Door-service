package com.jobdoor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.jobdoor.dto.UserDTO;
import com.jobdoor.service.UserService;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@RequestBody UserDTO registerUser) {
		userService.registeruser(registerUser);
		return new ResponseEntity<>("User Created successfully!", HttpStatus.CREATED);
	}
}
