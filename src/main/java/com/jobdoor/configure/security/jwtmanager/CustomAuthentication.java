package com.jobdoor.configure.security.jwtmanager;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.jobdoor.dao.AuthenticationDAO;
import com.jobdoor.exception.ResourceNotFoundException;
import com.jobdoor.model.UserModel;


@Component
public class CustomAuthentication implements UserDetailsService {

	@Autowired
	AuthenticationDAO authenticationDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserModel> user = authenticationDAO.findByUserName(username);
		return user.map(CustomAuthenticationSupport::new)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Name", username));
	}
}
