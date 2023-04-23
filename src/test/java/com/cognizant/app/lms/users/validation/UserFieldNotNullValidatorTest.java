package com.cognizant.app.lms.users.validation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserFieldNotNullValidatorTest {

	@Autowired
	private UserFieldNotNullValidator userFieldNotNullValidator;
	
	@Test
	public void testNullUserField() {
		boolean response = userFieldNotNullValidator.isValid(null, null);
		
		assertFalse(response);
	}
	
	@Test
	public void testEmptyUserField() {
		boolean response = userFieldNotNullValidator.isValid("",null);
		
		assertFalse(response);
	}
}
