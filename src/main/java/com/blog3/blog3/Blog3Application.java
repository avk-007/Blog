package com.blog3.blog3;

import com.blog3.blog3.entity.Role;
import com.blog3.blog3.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Blog3Application implements CommandLineRunner {
	@Autowired
	private RoleRepository roleRepository;


	public static void main(String[] args) {
		SpringApplication.run(Blog3Application.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();

	}
	@Override
	public void run(String... args) throws Exception {
		Role adminRole = new Role();
		adminRole.setName("ROLE_ADMIN");
		roleRepository.save(adminRole);

		Role userRole = new Role();
		userRole.setName("ROLE_USER");
		roleRepository.save(userRole);
	}


}
