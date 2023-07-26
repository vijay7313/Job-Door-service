package com.jobdoor.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.jobdoor.dao.AuthenticationDAO;
import com.jobdoor.dto.UserDTO;
import com.jobdoor.model.UserModel;
import com.jobdoor.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	AuthenticationDAO authenticationDAO;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserModel registeruser(UserDTO userDTO) {
		UUID uuid = UUID.randomUUID();
		userDTO.setUserId(uuid.toString());
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		// DTO to Entity
		UserModel userModel = modelMapper.map(userDTO, UserModel.class);
		return authenticationDAO.save(userModel);
	}

}
