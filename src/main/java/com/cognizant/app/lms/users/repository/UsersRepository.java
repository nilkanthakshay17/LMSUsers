package com.cognizant.app.lms.users.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.cognizant.app.lms.users.entity.UserEntity;

@Repository
public interface UsersRepository extends MongoRepository<UserEntity, Integer>{
	public UserEntity findByUserId(String userId);
	public UserEntity findByUserEmail(String email);
}
