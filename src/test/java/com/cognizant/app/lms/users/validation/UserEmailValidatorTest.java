package com.cognizant.app.lms.users.validation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserEmailValidatorTest {

	@Autowired
	private UserEmailValidator validator;

	@Test
	public void testValidatorForNullEmail() {
		boolean response = validator.isValid(null, null);
		
		assertFalse(response);
	}
	
	@Test
	public void testValidatorForEmptyEmail() {
		boolean response = validator.isValid("", null);
		
		assertFalse(response);
	}
	
	@Test
	public void testValidatorForValidEmail() {
		boolean response = validator.isValid("nilkanthakshay17@gmail.com", null);
		
		assertTrue(response);
	}
	
	@Test
	public void testValidatorForInValidEmail() {
		boolean response = validator.isValid("nilkanthakshay17", null);
		
		assertFalse(response);
	}
	
}
