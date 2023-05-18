package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserDto;

public interface UserService {
	
	//create 
	
	UserDto createUser(UserDto userDto);
	
	//update
	UserDto updateUser(UserDto userDto,String userId);
	
	//delete
	void deleteUser(String userId);
	
	//get all users
	List<UserDto> getAllUser();
	
	// get single user by id
	UserDto getUserById(String userId);
	
	//get single user by email
	UserDto getUserByEmail(String email);
	
	//search user
	List<UserDto> searchUser(String keyword);

}
