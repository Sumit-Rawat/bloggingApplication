package com.blog.blog_app_apis;

import com.blog.blog_app_apis.config.AppConstants;
import com.blog.blog_app_apis.entities.Role;
import com.blog.blog_app_apis.repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import java.util.List;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);


	}

	//get the object of model mapper class
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

	//to validate the methods that are annotated with @Valid or @Validated
	@Bean
	public static MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public void run(String... args) throws Exception {
		try {
			Role role = new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ADMIN_USER");

			Role role1 = new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("NORMAL_USER");

			List<Role> roles = List.of(role, role1);
			List<Role> result = this.roleRepo.saveAll(roles);
			result.forEach(r -> {
				System.out.println(r.getName());
			});
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
