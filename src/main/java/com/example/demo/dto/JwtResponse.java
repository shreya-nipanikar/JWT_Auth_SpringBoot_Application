package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {
    
	private String jwtToken;
	private UserDto user;
	
}
