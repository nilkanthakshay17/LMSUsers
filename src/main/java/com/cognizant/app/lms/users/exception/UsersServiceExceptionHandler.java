package com.cognizant.app.lms.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UsersServiceExceptionHandler {

	@ExceptionHandler(value = UsersServiceException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleUsersServiceException(UsersServiceException exception) {
		return exception.getLocalizedMessage();
	}
}
