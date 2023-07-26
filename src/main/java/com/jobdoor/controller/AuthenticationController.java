package com.jobdoor.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jobdoor.configure.security.jwtmanager.JWTTokenUtil;
import com.jobdoor.dto.UserDTO;
import com.jobdoor.utils.ResponseHandler;

@RestController
@CrossOrigin
public class AuthenticationController {

	@Autowired
	JWTTokenUtil jwtTokenUtil;

	@Autowired(required = true)
	AuthenticationManager authenticationManager;

	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticateAndGetToken(@RequestBody UserDTO userDTO) {

		Map<String, Object> apiData = new HashMap<String, Object>();
		Map<String, Object> errorData = new HashMap<>();

		try {

			Authentication authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userDTO.getUserName(), userDTO.getPassword()));

			String token = jwtTokenUtil.generateToken(userDTO.getUserName());

			apiData.put("message", "LoggedIn Successfully");
			apiData.put("statusCode", HttpStatus.OK.value());

			return ResponseHandler.generateResponse(token, apiData, null);
		} catch (BadCredentialsException badCredentialsException) {
			errorData.put("message", "Incorrect password");
			errorData.put("statusCode", HttpStatus.UNAUTHORIZED.value());

			return ResponseHandler.generateResponse("", "", errorData);
		} catch (AuthenticationException authenticationException) {
			errorData.put("message", "Invalid username");
			errorData.put("statusCode", HttpStatus.UNAUTHORIZED.value());

			return ResponseHandler.generateResponse("", "", errorData);
		}

	}
}
