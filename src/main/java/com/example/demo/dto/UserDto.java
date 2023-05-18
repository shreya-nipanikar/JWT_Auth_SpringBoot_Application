package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

	private String userId;
	private String name;
	private String email;
	private String password;
	private String gender;
	private String about;
    
	private Set<RoleDto> roles = new HashSet<>();
	
	
}
