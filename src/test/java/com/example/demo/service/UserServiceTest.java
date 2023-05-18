package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.dto.*;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {

	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private RoleRepository roleRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper mapper;
	
	User user;
	Role role;
	
	String roleId;
	
	//create dummy data to inject
	@BeforeEach
	public void init() {
		
		role = Role.builder()
				.roleId("abc")
				.roleName("NORMAL")
				.build();
		
		user = User.builder()
				.name("Shreya")
				.email("Shreya@gmail.com")
				.about("Friendly!")
				.gender("Female")
				.password("abcd")
				.roles(Set.of(role))
				.build();
		
		roleId = "abc";
	}
	
	// create User
	@Test
	public void createUserTest() {
		
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
		Mockito.when(roleRepository.findById(Mockito.anyString())).thenReturn(Optional.of(role));
		
		UserDto user1 = userService.createUser(mapper.map(user, UserDto.class));
		System.out.println(user1.getName());
		Assertions.assertNotNull(user1);
		
	}
	
	//update User
	@Test
	public void updateUserTest() {
		
		String userId="";
		
		UserDto userDto = UserDto.builder()
                          .name("Shreya Nipanikar")
                          .about("Non-Friendly")
                          .build();
		
		Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(user));
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
		
		UserDto updatedUser = userService.updateUser(userDto, userId);
		System.out.println(updatedUser.getName());
		Assertions.assertNotNull(updatedUser);
		
	}
	
	//delete User Test Case
	@Test
	public void deleteUserTest() {
		
		String userId="";
		
		Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(user));
		
		userService.deleteUser(userId);
		
		Mockito.verify(userRepository,Mockito.times(1)).delete(user);
		
	}
	
	//get All Users
	@Test
	public void getAllUserTest() {
		
		User user1 = User.builder()
				.name("Yashesh")
				.email("Yash@gmail.com")
				.about("Friendly!")
				.gender("Male")
				.password("abcd")
				.roles(Set.of(role))
				.build();
		
		User user2 = User.builder()
				.name("Durvesh")
				.email("Durvesh@gmail.com")
				.about("Friendly!")
				.gender("Male")
				.password("abcd")
				.roles(Set.of(role))
				.build();
		
		List<User> userList = Arrays.asList(user1,user2);
		
		
		Mockito.when(userRepository.findAll()).thenReturn(userList);
		
		//Call the method under test
		List<UserDto> result = userService.getAllUser();
		
		Assertions.assertEquals(2,result.size());
		Assertions.assertEquals(user1.getName(),result.get(0).getName());
		Assertions.assertEquals(user2.getName(),result.get(1).getName());
		
	}
	
	@Test
	public void getUserByIdTest() {
		
		String userId="";
		
		Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(user));
		
		UserDto userByIdDto = userService.getUserById(userId);
		                       
		//Actual vs Excepted
		Assertions.assertNotNull(userByIdDto);
		Assertions.assertEquals(user.getName(),userByIdDto.getName(),"Name not matched");
		
	}
	
	@Test
	public void getUserByEmailTest() {
		 String email="";
		 
		 Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));
		 UserDto userEmailDto = userService.getUserByEmail(email);
		 
		 Assertions.assertNotNull(userEmailDto);
		 Assertions.assertEquals(user.getEmail(), userEmailDto.getEmail(),"Not Not matched");
		 
	}
	
	@Test
	public void searchUserTest() {
		
		String keyword = "";
		
		User user1 = User.builder()
				.name("Yashesh")
				.email("Yash@gmail.com")
				.about("Friendly!")
				.gender("Male")
				.password("abcd")
				.roles(Set.of(role))
				.build();
		
		List<User> userList = Arrays.asList(user,user1);
		
		Mockito.when(userRepository.findByNameContaining(Mockito.anyString())).thenReturn(userList);
		
		List<UserDto> userDtos = userService.searchUser(keyword);
		
		Assertions.assertNotNull(userDtos);
		Assertions.assertEquals(2,userDtos.size(),"Oops size not match!");
		Assertions.assertEquals(user.getName(),userDtos.get(0).getName());
	
		
		
	}
	
	
	
	
}
