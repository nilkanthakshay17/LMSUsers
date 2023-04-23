package com.cognizant.app.lms.users.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.cognizant.app.lms.users.entity.UserEntity;


@DataMongoTest
class UsersRepositoryTest {
	
	@Autowired
	private UsersRepository usersRepository;
	
	UserEntity userEntity;
	
	@BeforeEach
	public void setup() {
		userEntity = new UserEntity("555","Akshay Nilkanth", "nilkanthakshay555@gmail.com", true, "555", "ROLE_USER,ROLE_ADMIN");
		usersRepository.save(userEntity);
	}
	
	
	@AfterEach
	public void cleanup() {
		usersRepository.delete(userEntity);
	}
	
	@Test
	public void testFindByUserId() {
		UserEntity receivedUserEntity = usersRepository.findByUserId("555");
		assertEquals("555", receivedUserEntity.getUserId());
		assertEquals("Akshay Nilkanth", receivedUserEntity.getUserName());
		assertEquals("nilkanthakshay555@gmail.com", receivedUserEntity.getUserEmail());
		assertEquals("ROLE_USER,ROLE_ADMIN", receivedUserEntity.getRoles());
	}
	
	
	@Test
	public void testFindByUserEmail() {
		UserEntity receivedUserEntity = usersRepository.findByUserEmail("nilkanthakshay555@gmail.com");
		assertEquals("555", receivedUserEntity.getUserId());
		assertEquals("Akshay Nilkanth", receivedUserEntity.getUserName());
		assertEquals("nilkanthakshay555@gmail.com", receivedUserEntity.getUserEmail());
		assertEquals("ROLE_USER,ROLE_ADMIN", receivedUserEntity.getRoles());
	}
	

	
}
