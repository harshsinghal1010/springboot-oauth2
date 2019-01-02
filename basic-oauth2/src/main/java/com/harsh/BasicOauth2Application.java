package com.harsh;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.harsh.entity.Role;
import com.harsh.entity.User;
import com.harsh.service.UserService;

@SpringBootApplication
public class BasicOauth2Application {

	public static void main(String[] args) {
		SpringApplication.run(BasicOauth2Application.class, args);
	}
	
	 @Bean
	    public CommandLineRunner setupDefaultUser(UserService service) {
	        return args -> {
	            service.save(new User(
	                    "user", //username
	                    "user", //password
	Arrays.asList(new Role("USER"), new Role("ACTUATOR")),//roles 
	                    true//Active
	            ));
	        };
	    }
	    
	    @Bean
	    public PasswordEncoder getPasswordEncoder(){
	        return new BCryptPasswordEncoder();
	    }    
}

