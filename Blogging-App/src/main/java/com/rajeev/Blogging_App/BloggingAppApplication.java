package com.rajeev.Blogging_App;

import com.rajeev.Blogging_App.Configuration.AppConstants;
import com.rajeev.Blogging_App.Model.Role;
import com.rajeev.Blogging_App.Repository.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BloggingAppApplication implements CommandLineRunner {


	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BloggingAppApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {

        Role admin = new Role();
		admin.setId(AppConstants.ADMIN_ROLE);
		admin.setName("ADMIN");

		Role user = new Role();
		user.setId(AppConstants.NORMAL_ROLE);
		user.setName("USER");

		List<Role> roles = List.of(admin , user);
		roleRepo.saveAll(roles);
	}
}
