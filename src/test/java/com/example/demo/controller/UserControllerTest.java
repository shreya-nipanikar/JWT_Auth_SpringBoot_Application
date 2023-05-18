package com.example.demo.controller;

import java.util.List;
import java.util.Set;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.dto.*;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
  
	@MockBean
	private UserService userService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ModelMapper mapper;
	
	private Role role;
	
	private User user;
	
	@BeforeEach
	public void init() {
		
		role = Role.builder().roleId("abc").roleName("NORMAL").build();

        user = User.builder()
                .name("Shreya")
                .email("Shreya@gmail.com")
                .about("This is testing create method")
                .gender("Male")
                .password("lcwd")
                .roles(Set.of(role))
                .build();
		
		
	}
	
	
	@Test
	public void createUserTest() throws Exception {
		
		//Home + POST + user data as json
		// data as json + Status created.
		
		UserDto dto = mapper.map(user, UserDto.class);
		
		Mockito.when(userService.createUser(Mockito.any())).thenReturn(dto);
		
		//actual request for URL
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/Home")
		                    .contentType(MediaType.APPLICATION_JSON)
		                    .content(convertObjectToJsonString(user))
		                    .accept(MediaType.APPLICATION_JSON))
                     .andDo(print())
                     .andExpect(status().isCreated())
                     .andExpect(jsonPath("$.name").exists());
		
	}
    
	//Test private api. 
	 @Test
	 public void updateUserTest() throws Exception {
		 // /Home/{userId} + Put request +json
		 
		 String userId = "123";
		 UserDto dto = this.mapper.map(user,UserDto.class);
		 
		 Mockito.when(userService.updateUser(Mockito.any(), Mockito.anyString())).thenReturn(dto);
		 
		 this.mockMvc.perform(MockMvcRequestBuilders.put("/Home/" + userId )
				                      .header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYWtzaEBnbWFpbC5jb20iLCJpYXQiOjE2ODQ0NDA3MTUsImV4cCI6MTY4NDQ2OTUxNX0.kXPM-5gF4y4csfB4FvWZb0dImC-yyBKGp0H29kxohQM")
				                      .contentType(MediaType.APPLICATION_JSON)
				                      .content(convertObjectToJsonString(user))
				                      .accept(MediaType.APPLICATION_JSON)
				 
				 )
		         .andDo(print())
		         .andExpect(status().isOk())
		         .andExpect(jsonPath("$.name").exists());
		 
		 
	 }
	 
	 //get All Users
	 
	 @Test
	 public void getAllUsersTest() throws Exception {
		 
		 UserDto user1 = UserDto.builder()
                          .name("Shreya")
                          .email("shreya@gmail.com")
                          .password("abcd")
                          .about("Friendly")
                          .gender("Female")
                          .build();
		 UserDto user2 = UserDto.builder()
                 .name("Sheni")
                 .email("sheni@gmail.com")
                 .password("abcd")
                 .about("Non-Friendly")
                 .gender("Female")
                 .build();
		 
		 List<UserDto> userDtos = Arrays.asList(user1,user2);
		 
		 Mockito.when(userService.getAllUser()).thenReturn(userDtos);
		 
		 this.mockMvc.perform(MockMvcRequestBuilders.get("/Home")
				              .header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYWtzaEBnbWFpbC5jb20iLCJpYXQiOjE2ODQ0NDA3MTUsImV4cCI6MTY4NDQ2OTUxNX0.kXPM-5gF4y4csfB4FvWZb0dImC-yyBKGp0H29kxohQM")
				              .contentType(MediaType.APPLICATION_JSON)
				              .accept(MediaType.APPLICATION_JSON)
				                  )
		                  .andDo(print())
		                  .andExpect(status().isOk());
		 
	 }


	private String convertObjectToJsonString(Object user) {
		
		try {
			return new ObjectMapper().writeValueAsString(user);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}
