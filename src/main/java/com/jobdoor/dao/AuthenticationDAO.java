package com.jobdoor.dao;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jobdoor.model.UserModel;



@EnableScan
@Repository
public interface AuthenticationDAO extends CrudRepository<UserModel, String> {

	Optional<UserModel> findByUserName(String username);
}

