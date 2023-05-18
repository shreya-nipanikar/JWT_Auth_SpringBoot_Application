package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;

@SpringBootApplication
public class DtoTryApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DtoTryApplication.class, args);
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository repository;
	
	@Value("${normal.role.id}")
	private String role_normal_id;
	
	@Value("${admin.role.id}")
	private String role_admin_id;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(passwordEncoder.encode("abcd"));
		
		
		try {
			
			Role role_admin = Role.builder()
                              .roleId(role_admin_id)
                              .roleName("ROLE_ADMIN")
                              .build();
			Role role_normal = Role.builder()
                    .roleId(role_normal_id)
                    .roleName("ROLE_NORMAL")
                    .build();
			
			repository.save(role_admin);
			repository.save(role_normal);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	

}
