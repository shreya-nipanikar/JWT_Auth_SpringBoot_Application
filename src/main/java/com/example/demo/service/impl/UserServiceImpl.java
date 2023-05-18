package com.example.demo.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ModelMapper mapper;
	
	@Value("${normal.role.id}")
	private String normalRoleId;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		//Generate Unique Id in String format
		String userId = UUID.randomUUID().toString();
		userDto.setUserId(userId);
		
		//Encode and set password
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		//dto => entity
		User userEt = dtoToEntity(userDto);
		
		//fetch role of normal and set it to user
		Role role = roleRepository.findById(normalRoleId).get();
		userEt.getRoles().add(role);
		User savedUser = userRepository.save(userEt);
		
		//Entity => DTO
		UserDto newDto = entityToDto(savedUser);
		return newDto;
		
	}


	@Override
	public UserDto updateUser(UserDto userDto, String userId) {
		
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with given id!"));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setGender(userDto.getGender());
		user.setPassword(userDto.getPassword());
		
		//save data
		User updatedUser = userRepository.save(user);
		UserDto updatedDto = entityToDto(updatedUser);
		return updatedDto;
	}

	@Override
	public void deleteUser(String userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with given id!"));
		userRepository.delete(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		
		List<User> users = userRepository.findAll();
		List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
		
		return dtoList;
	}

	@Override
	public UserDto getUserById(String userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with given id!"));
		return entityToDto(user);
	}
    
	
	//Custom finder Method or Query Methods
	@Override
	public UserDto getUserByEmail(String email) {
		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found with given Email!"));
		return entityToDto(user);
	}

	@Override
	public List<UserDto> searchUser(String keyword) {
		List<User> users = userRepository.findByNameContaining(keyword);
		List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
		return dtoList;
	}
	
	
	private UserDto entityToDto(User savedUser) {
		
//		UserDto userDto = UserDto.builder()
//                          .userId(savedUser.getUserId())
//                          .name(savedUser.getName())
//                          .email(savedUser.getEmail())
//                          .password(savedUser.getPassword())
//                          .gender(savedUser.getGender())
//                          .about(savedUser.getAbout())
//                          .build();
//                          
		return mapper.map(savedUser, UserDto.class);
	}
  
	
	private User dtoToEntity(UserDto userDto) {
		
//	User user =	User.builder()
//		       .userId(userDto.getUserId())
//		       .name(userDto.getName())
//		       .email(userDto.getEmail())
//		       .password(userDto.getPassword())
//		       .gender(userDto.getGender())
//		       .about(userDto.getAbout()).build();
//		
		return mapper.map(userDto, User.class);
	}
	
	

}
