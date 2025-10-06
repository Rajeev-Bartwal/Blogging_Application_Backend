package com.rajeev.Blogging_App;

import com.rajeev.Blogging_App.Configuration.AppConstants;
import com.rajeev.Blogging_App.Model.Role;
import com.rajeev.Blogging_App.Repository.RoleRepo;
import io.github.cdimascio.dotenv.Dotenv;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class BloggingAppApplication implements CommandLineRunner {


	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {

//        Dotenv dotenv = Dotenv.configure()
//                .directory("C:/Spring_Project/Blogging-App/Blogging-App/")
//                .filename(".env")
//                .load();
//        System.setProperty("DB_PASSWORD" , Objects.requireNonNull(dotenv.get("DB_PASSWORD")));
//        System.setProperty("DB_URL" , Objects.requireNonNull(dotenv.get("DB_URL")));
//        System.setProperty("DB_USER" , Objects.requireNonNull(dotenv.get("DB_USER")));
//        System.setProperty("CLOUDINARY_CLOUD_NAME", Objects.requireNonNull(dotenv.get("CLOUDINARY_CLOUD_NAME")));
//        System.setProperty("CLOUDINARY_API_KEY", Objects.requireNonNull(dotenv.get("CLOUDINARY_API_KEY")));
//        System.setProperty("CLOUDINARY_API_SECRET", Objects.requireNonNull(dotenv.get("CLOUDINARY_API_SECRET")));
//

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
