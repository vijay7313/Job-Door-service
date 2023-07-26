package com.jobdoor.service;

import com.jobdoor.dto.UserDTO;
import com.jobdoor.model.UserModel;

public interface UserService {

	UserModel registeruser(UserDTO user);
}
