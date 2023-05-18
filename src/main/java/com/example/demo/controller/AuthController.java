package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UserService;
import com.example.demo.dto.*;
import com.example.demo.exceptions.BadApiRequestException;
import com.example.demo.security.JwtHelper;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtHelper helper;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){
		this.doAuthenticate(request.getEmail(),request.getPassword());	
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		String token = this.helper.generateToken(userDetails);
		
		UserDto userDto = modelMapper.map(userDetails,UserDto.class);
		
		JwtResponse response = JwtResponse.builder()
				      .jwtToken(token)
				      .user(userDto).build();
		
		return new ResponseEntity<>(response,HttpStatus.OK);
		
		
	}

	private void doAuthenticate(String email, String password) {
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email,password);
		
		try {
			manager.authenticate(authentication);
		}catch(BadCredentialsException e) {
			throw new BadApiRequestException("Invalid Username or Password Exception");
		}
		
	}
}
