package com.cognizant.app.lms.users.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.app.lms.users.dto.UserDTO;
import com.cognizant.app.lms.users.model.UserRequestModel;
import com.cognizant.app.lms.users.model.UserResponseModel;
import com.cognizant.app.lms.users.model.UserUpdateRequestModel;
import com.cognizant.app.lms.users.service.UsersServiceInt;

@RestController
@RequestMapping("/user")
public class UsersController {

	private UsersServiceInt usersServiceInt;
	
	private ModelMapper modelMapper;
	
	@Autowired
	public UsersController(UsersServiceInt usersServiceInt) {
		this.usersServiceInt = usersServiceInt;
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	@GetMapping("/status")
	public String getStatus() {
		return "Working...";
	}
	
	@PostMapping
	public ResponseEntity<UserResponseModel> createUser(@RequestBody UserRequestModel createRequest){
		UserDTO creatUserRequestDto = modelMapper.map(createRequest, UserDTO.class);
		
		UserDTO createdUserDto = usersServiceInt.createUser(creatUserRequestDto);
		
		UserResponseModel createdUserRespone = modelMapper.map(createdUserDto, UserResponseModel.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUserRespone);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseModel> getUserById(@PathVariable(name = "id")String id ){
		
		UserDTO receivedUserDto = usersServiceInt.getUserById(id);
		
		UserResponseModel receivedUserResponse = modelMapper.map(receivedUserDto, UserResponseModel.class);
		
		return ResponseEntity.status(HttpStatus.OK).body(receivedUserResponse);
	}
	
	@GetMapping
	public ResponseEntity<List<UserResponseModel>> getAllUsers(){
		
		List<UserDTO> allUsersDto = usersServiceInt.getAllUsers();
		List<UserResponseModel> allUsersResponse = new ArrayList<>();
		
		for(UserDTO ud : allUsersDto) {
			allUsersResponse.add(modelMapper.map(ud, UserResponseModel.class));
		}
		return ResponseEntity.status(HttpStatus.OK).body(allUsersResponse);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<UserResponseModel> getUserByEmailId(@PathVariable(name = "email")String email){
		UserDTO receivedUserDto = usersServiceInt.getUserByEmail(email);
		UserResponseModel receivedUserResponse = modelMapper.map(receivedUserDto, UserResponseModel.class);
		return ResponseEntity.status(HttpStatus.OK).body(receivedUserResponse);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserResponseModel> updateuserById(@RequestBody UserUpdateRequestModel updateRequestModel,@PathVariable(name = "id")String id){
		UserDTO userDtoToUpdate = modelMapper.map(updateRequestModel, UserDTO.class);
		
		UserDTO updatedUserDto = usersServiceInt.updateUserById(userDtoToUpdate, id);
		
		UserResponseModel updatedUserResponse = modelMapper.map(updatedUserDto, UserResponseModel.class);
		
		return ResponseEntity.status(HttpStatus.OK).body(updatedUserResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, UserResponseModel>> deleteUserById(@PathVariable(name = "id")String id){
		UserDTO deletedUserDto = usersServiceInt.deleteUserById(id);
		
		UserResponseModel deletedUserResponse = modelMapper.map(deletedUserDto, UserResponseModel.class);
		
		Map<String, UserResponseModel> deleteResMap = new HashMap<>();
		
		deleteResMap.put("Deleted User - ", deletedUserResponse);
		
		return ResponseEntity.status(HttpStatus.OK).body(deleteResMap);
	}
	
	@DeleteMapping
	public String deleteAllUser() {
		return usersServiceInt.deletAllUsers();
	}
	
}
