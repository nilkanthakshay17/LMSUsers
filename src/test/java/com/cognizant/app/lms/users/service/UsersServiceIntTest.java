package com.cognizant.app.lms.users.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cognizant.app.lms.users.dto.UserDTO;
import com.cognizant.app.lms.users.entity.UserEntity;
import com.cognizant.app.lms.users.repository.UsersRepository;

@SpringBootTest
class UsersServiceIntTest {


	@Autowired
	private UsersServiceInt useServiceInt;

	@MockBean
	private UsersRepository usersRepository;
	
	@BeforeEach
	void setup() {
		UserEntity userEntity = new UserEntity("123","Akshay Nilkanth", "nilkanthakshay17@gmail.com", false, "1234567", "ROLE_USER,ROLE_ADMIN");
		Mockito.when(usersRepository.findByUserId("123")).thenReturn(userEntity);
		Mockito.when(usersRepository.findByUserEmail("nilkanthakshay17@gmail.com")).thenReturn(userEntity);
	}
	
	@Test
	public void testGetUserById() {
		String userId ="123";
		UserDTO userDto = useServiceInt.getUserById(userId);
		
		assertEquals(userId, userDto.getUserId());
		assertEquals("Akshay Nilkanth", userDto.getUserName());
		assertEquals("nilkanthakshay17@gmail.com", userDto.getUserEmail());
		assertEquals("ROLE_USER,ROLE_ADMIN", userDto.getRoles());
	}


	@Test
	public void testGetUserByEmail() {
		String userEmail ="nilkanthakshay17@gmail.com";
		UserDTO userDto = useServiceInt.getUserByEmail(userEmail);
		
		
		assertEquals(userEmail, userDto.getUserEmail());
		assertEquals("123", userDto.getUserId());
		assertEquals("Akshay Nilkanth", userDto.getUserName());
		assertEquals("ROLE_USER,ROLE_ADMIN", userDto.getRoles());
	}
}
