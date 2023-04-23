package com.cognizant.app.lms.users.validation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserPasswordValidatorTest {

	@Autowired
	private UserPasswordValidator userPasswordValidator;

	@Test
	public void testValidatorForNullPassword() {
		boolean response = userPasswordValidator.isValid(null, null);
		
		assertFalse(response);
	}
	
	@Test
	public void testValidatorForEmptyPassword() {
		boolean response = userPasswordValidator.isValid("", null);
		
		assertFalse(response);
	}
	
	@Test
	public void testValidatorForValidPassword() {
		boolean response = userPasswordValidator.isValid("Shadyaxee@123", null);
		
		assertTrue(response);
	}
	
	@Test
	public void testValidatorForInValidPassword() {
		boolean response = userPasswordValidator.isValid("Shadyaxee123", null);
		
		assertFalse(response);
	}
}
