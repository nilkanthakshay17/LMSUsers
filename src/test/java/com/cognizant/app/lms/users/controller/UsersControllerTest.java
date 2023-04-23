package com.cognizant.app.lms.users.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognizant.app.lms.users.dto.UserDTO;
import com.cognizant.app.lms.users.model.UserRequestModel;
import com.cognizant.app.lms.users.model.UserResponseModel;
import com.cognizant.app.lms.users.service.UsersServiceInt;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UsersController.class)
class UsersControllerTest {

	@Autowired
	MockMvc mvc;
	
	@MockBean
	private UsersServiceInt usersServiceInt;
	
	@MockBean
	private ModelMapper modelMapper;
	
	UserRequestModel userRequestModel;
	UserDTO userDTO;
	UserResponseModel userResponseModel;
	
	
	@BeforeEach
	public void setup() {
		userRequestModel = new UserRequestModel(
				"Akshay Nilkanth",
				"nilkanthakshay17@gmail.com",
				"akshay@123",
				true,
				"ROLE_USER,ROLE_ADMIN"
				);
		
		userDTO = new UserDTO(
				"9999",
				"Akshay Nilkanth",
				"nilkanthakshay17@gmail.com",
				true,
				"akshay@123",
				"encryptedpass",
				"ROLE_USER,ROLE_ADMIN"
				);
		
		userResponseModel = new UserResponseModel(
				"9999",
				"Akshay Nilkanth",
				"nilkanthakshay17@gmail.com",
				true,
				"encryptedpass",
				"ROLE_USER,ROLE_ADMIN"
				);
	}
	
	@Test
	public void testCreateUser() throws Exception{
		String createUserURI = "/user";
		
		String inputJson = mapToJson(userRequestModel);
		
		when(modelMapper.map(any(),eq(UserDTO.class))).thenReturn(userDTO);
		when(usersServiceInt.createUser(eq(userDTO))).thenReturn(userDTO);
		when(modelMapper.map(any(), eq(UserResponseModel.class))).thenReturn(userResponseModel);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(createUserURI)
				.contentType(MediaType.APPLICATION_JSON)
				.content(inputJson))
				.andReturn();
		
		assertEquals(201, mvcResult.getResponse().getStatus());
		
		UserResponseModel userResponseModelResult = mapFromJson(mvcResult.getResponse().getContentAsString(), UserResponseModel.class);
		
		assertEquals(userResponseModelResult.getUserId(), userResponseModel.getUserId());
		assertEquals(userResponseModelResult.getUserName(), userResponseModel.getUserName());
		assertEquals(userResponseModelResult.getUserEmail(), userResponseModel.getUserEmail());
		assertEquals(userResponseModelResult.getEncryptedPassword(), userResponseModel.getEncryptedPassword());
		assertEquals(userResponseModelResult.getRoles(), userResponseModel.getRoles());
	}

	
	@Test
	public void testGetUserByUserId() throws Exception{
		String getUserByUserIdURI = "/user/9999";
		
		when(usersServiceInt.getUserById(eq("9999"))).thenReturn(userDTO);
		when(modelMapper.map(any(), eq(UserResponseModel.class))).thenReturn(userResponseModel);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(getUserByUserIdURI)
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		
		assertEquals(200, mvcResult.getResponse().getStatus());
		
		UserResponseModel userResponseModelResult = mapFromJson(mvcResult.getResponse().getContentAsString(), UserResponseModel.class);
		
		assertEquals(userResponseModelResult.getUserId(), userResponseModel.getUserId());
		assertEquals(userResponseModelResult.getUserName(), userResponseModel.getUserName());
		assertEquals(userResponseModelResult.getUserEmail(), userResponseModel.getUserEmail());
		assertEquals(userResponseModelResult.getEncryptedPassword(), userResponseModel.getEncryptedPassword());
		assertEquals(userResponseModelResult.getRoles(), userResponseModel.getRoles());
	}
	
	@Test
	public void testGetUserByEmail() throws Exception{
		String getUserByEmailURI = "/user/email/nilkanthakshay17@gmail.com";
		
		when(usersServiceInt.getUserByEmail(eq("nilkanthakshay17@gmail.com"))).thenReturn(userDTO);
		when(modelMapper.map(any(), eq(UserResponseModel.class))).thenReturn(userResponseModel);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(getUserByEmailURI)
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		
		assertEquals(200, mvcResult.getResponse().getStatus());
		
		UserResponseModel userResponseModelResult = mapFromJson(mvcResult.getResponse().getContentAsString(), UserResponseModel.class);
		
		assertEquals(userResponseModelResult.getUserId(), userResponseModel.getUserId());
		assertEquals(userResponseModelResult.getUserName(), userResponseModel.getUserName());
		assertEquals(userResponseModelResult.getUserEmail(), userResponseModel.getUserEmail());
		assertEquals(userResponseModelResult.getEncryptedPassword(), userResponseModel.getEncryptedPassword());
		assertEquals(userResponseModelResult.getRoles(), userResponseModel.getRoles());
	}
	
	@Test
	public void testGetAllUsers() throws Exception{
		String getAllUsersURI = "/user";
		
		List<UserDTO> allDtos = new ArrayList<>();
		allDtos.add(userDTO);
		allDtos.add(userDTO);
		
		when(usersServiceInt.getAllUsers()).thenReturn(allDtos);
		when(modelMapper.map(any(), eq(UserResponseModel.class))).thenReturn(userResponseModel);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(getAllUsersURI)
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		
		assertEquals(200, mvcResult.getResponse().getStatus());
		
		UserResponseModel[] userResponseModelResult = mapFromJson(mvcResult.getResponse().getContentAsString(), UserResponseModel[].class);
		
		assertNotNull(userResponseModelResult);
	}
	
	
	@Test
	public void testUpdateUser() throws Exception{
		String updateUserURI = "/user/9999";
		
		userRequestModel.setUserName("Akshay Nilkanth 9999");
		userRequestModel.setUserEmail("nilkanthakshay999@gmail.com");
		
		userDTO.setUserName("Akshay Nilkanth 9999");
		userDTO.setUserEmail("nilkanthakshay999@gmail.com");
		
		userResponseModel.setUserName("Akshay Nilkanth 9999");
		userResponseModel.setUserEmail("nilkanthakshay999@gmail.com");
		
		
		String inputJson = mapToJson(userRequestModel);
		
		when(modelMapper.map(any(),eq(UserDTO.class))).thenReturn(userDTO);
		when(usersServiceInt.updateUserById(eq(userDTO), eq("9999"))).thenReturn(userDTO);
		when(modelMapper.map(any(), eq(UserResponseModel.class))).thenReturn(userResponseModel);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(updateUserURI)
				.contentType(MediaType.APPLICATION_JSON)
				.content(inputJson))
				.andReturn();
		
		assertEquals(202, mvcResult.getResponse().getStatus());
		
		UserResponseModel userResponseModelResult = mapFromJson(mvcResult.getResponse().getContentAsString(), UserResponseModel.class);
		
		assertEquals(userResponseModelResult.getUserId(), userResponseModel.getUserId());
		assertEquals(userResponseModelResult.getUserName(), userResponseModel.getUserName());
		assertEquals(userResponseModelResult.getUserEmail(), userResponseModel.getUserEmail());
		assertEquals(userResponseModelResult.getEncryptedPassword(), userResponseModel.getEncryptedPassword());
		assertEquals(userResponseModelResult.getRoles(), userResponseModel.getRoles());
	}

	
	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
}
