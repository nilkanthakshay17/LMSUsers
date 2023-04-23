package com.cognizant.app.lms.users.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cognizant.app.lms.users.dto.UserDTO;

@Service
public interface UsersServiceInt {
	public UserDTO createUser(UserDTO createUserDto);
	public UserDTO getUserById(String userId);
	public List<UserDTO> getAllUsers();
	public UserDTO updateUserById(UserDTO userUpdateRequestDTO,String userId);
	public UserDTO deleteUserById(String userId);
	public String deletAllUsers();
	public UserDTO getUserByEmail(String email);
}
